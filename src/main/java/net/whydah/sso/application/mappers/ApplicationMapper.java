package net.whydah.sso.application.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.application.types.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ApplicationMapper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationMapper.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Application application) {
        String applicationCreatedJson = null;
        try {
            applicationCreatedJson = mapper.writeValueAsString(application);
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


    //Should probably use JsonPath
    public static Application fromJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Application application = mapper.readValue(json, Application.class);
            return application;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error mapping json for " + json, e);
        }
    }

    public static List<Application> fromJsonList(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Application> applications = mapper.readValue(json, new TypeReference<List<Application>>() { });
            return applications;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error mapping json for " + json, e);
        }
    }

}
