package net.whydah.sso.application.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.application.types.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ApplicationMapper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationMapper.class);
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static String toJson(Application application) {
        String applicationCreatedJson = null;
        try {
            applicationCreatedJson = mapper.writeValueAsString(application);
        } catch (IOException e) {
            log.warn("Could not convert application to Json {}", application.toString());
        }
        return applicationCreatedJson;
    }

    public static String toSafeJson(Application application) {
        String applicationCreatedJson = null;
        try {
            Application myApplication = ApplicationMapper.fromJson("" + ApplicationMapper.toJson(application));
            //myApplication.setSecurity(null);
            myApplication.getSecurity().setSecret("*************");
            myApplication.getSecurity().setAllowedIpAddresses(null);

            // //
            applicationCreatedJson = mapper.writeValueAsString(myApplication);
        } catch (IOException e) {
            log.warn("Could not convert application to Json {}", application.toString());
        }
        return applicationCreatedJson;
    }

    public static String toSafeJson(List<Application> applications) {

        String result = "[\n";
        for (Application app : applications) {
            result = result + toSafeJson(app) + ",";
        }
        return result.substring(0, result.length() - 1) + "\n]";
    }


    public static String toShortListJson(Application application) {
        String result = "{\n" +
                "  \"id\" : \"" + application.getId() + "\",\n" +
                "  \"name\" : \"" + application.getName() + "\",\n" +
                "  \"company\" : \"" + application.getCompany() + "\",\n" +
                "  \"tags\" : \"" + application.getTags() + "\",\n" +
                "  \"applicationUrl\" : \"" + application.getApplicationUrl() + "\",\n" +
                "  \"logoUrl\" : \"" + application.getLogoUrl() + "\"\n" +
                "}\n";
        return result;
    }

    public static String toShortListJson(List<Application> applications) {

        String result = "[\n";
        for (Application app : applications) {
            result = result + toShortListJson(app) + ",";
        }
        return result.substring(0, result.length() - 1) + "\n]";
    }

    public static String toPrettyJson(Application application) {
        String applicationCreatedJson = null;
        try {
            applicationCreatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(application);
        } catch (IOException e) {
            log.warn("Could not convert application to Json {}", application.toString());
        }
        return applicationCreatedJson;
    }


    //list of application data, no wrapping element "applications". Need to decide.
    public static String toJson(List<Application> applications) {
        String applicationCreatedJson = null;
        try {
            applicationCreatedJson = mapper.writeValueAsString(applications);
        } catch (IOException e) {
            log.warn("Could not convert applications to Json.");
        }
        return applicationCreatedJson;
    }

    //list of application data, no wrapping element "applications". Need to decide.
    public static String toPrettyJson(List<Application> applications) {
        String applicationCreatedJson = null;
        try {
            applicationCreatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(applications);
        } catch (IOException e) {
            log.warn("Could not convert applications to Json.");
        }
        return applicationCreatedJson;
    }


    //Should probably use JsonPath
    public static Application fromJson(String json) {
        try {
            Application application = mapper.readValue(json, Application.class);
            return application;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error mapping json for " + json, e);
        }
    }

    public static List<Application> fromJsonList(String json) {
        try {
            List<Application> applications = mapper.readValue(json, new TypeReference<List<Application>>() {
            });
            return applications;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error mapping json for " + json, e);
        }
    }

}
