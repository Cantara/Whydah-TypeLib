package net.whydah.sso.extensions.useractivity.helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.basehelpers.JsonPathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserActivityHelper {

    private static final Logger log = LoggerFactory.getLogger(UserActivityHelper.class);
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static List<String> getDataElementsFromUserActivityJson(String userActivityJson) {
        try {
            if (userActivityJson == null) {
                log.trace("getDataElementsFromUserActivityJson was empty, so returning null.");
            } else {
                List<String> applications = JsonPathHelper.findJsonpathList(userActivityJson, "$..data");
                if (applications == null) {
                    log.debug("jsonpath returned zero hits");
                    return null;
                }
                return applications;
//                return mapper.writeValueAsString(applications);
            }
        } catch (Exception e) {
            log.warn("Could not convert getDataElementsFromUserActivityJson Json}");
        }

        return null;
    }

    public static String getDataElementsJsonFromUserActivityJson(String userActivityJson) {
        try {
            if (userActivityJson == null) {
                log.trace("getDataElementsFromUserActivityJson was empty, so returning null.");
            } else {
                List<String> applications = JsonPathHelper.findJsonpathList(userActivityJson, "$..data");
                if (applications == null) {
                    log.debug("jsonpath returned zero hits");
                    return null;
                }
                return mapper.writeValueAsString(applications);
            }
        } catch (Exception e) {
            log.warn("Could not convert getDataElementsFromUserActivityJson Json}");
        }

        return null;
    }
}
