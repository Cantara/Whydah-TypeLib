package net.whydah.sso.application.helpers;

import com.jayway.jsonpath.JsonPath;
import net.whydah.sso.basehelpers.JsonPathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApplicationJsonpathHelper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationJsonpathHelper.class);


    public static String[] getApplicationNamesFromApplicationsJson(String applicationsJson) {
        if (applicationsJson == null) {
            log.debug("getApplicationNamesFromApplicationsJson was empty, so returning null.");
        } else {
            List<String> applications = JsonPathHelper.findJsonpathList(applicationsJson, "$.[*]name");
            if (applications == null) {
                log.debug("Xpath returned zero hits");
                return null;
            }
            String[] result = new String[applications.size()];
            return applications.toArray(result);
        }
        return null;
    }

    public static String[] getApplicationsFromApplicationsJson(String applicationsJson) {
        if (applicationsJson == null) {
            log.debug("getApplicationNamesFromApplicationsJson was empty, so returning null.");
        } else {
            List<String> applications = JsonPathHelper.findJsonpathList(applicationsJson, "$.applications.*");
            if (applications == null) {
                log.debug("JsonPath returned zero hits");
                return null;
            }
            String jsonString = applications.toString().substring(1, applications.toString().lastIndexOf("]") - 1);
            jsonString.replace("},{", " ,");
            String[] array = jsonString.split(" ");
            return array;
        }
        return null;

    }

    public static String findApplicationNameFromApplicationId(String applicationsJson) {
        if (applicationsJson == null) {
            log.debug("findApplicationNameFromApplicationId was empty, so returning null.");
        } else {
            String jsonString = JsonPathHelper.getJsonArrayFromJsonpathExpression(applicationsJson, "$.[?(@.id==12)].name").toJSONString();
            return JsonPath.parse(jsonString).read("$.[0]");

        }
        return null;
    }


}
