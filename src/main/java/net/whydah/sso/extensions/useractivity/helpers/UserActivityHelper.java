package net.whydah.sso.extensions.useractivity.helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.basehelpers.JsonPathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static String getUserSessionsJsonFromUserActivityJson(String userActivityJson) {
        try {
            if (userActivityJson == null) {
                log.trace("getDataElementsFromUserActivityJson was empty, so returning null.");
            } else {
                List<String> applications = JsonPathHelper.findJsonpathList(userActivityJson, "$..userSessions.*");
                if (applications == null) {
                    log.debug("jsonpath returned zero hits");
                    return null;
                }
                List<Map> userSessions = new LinkedList<>();

                final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                Calendar c = new GregorianCalendar();

                int i = 0;
                while (i < applications.size()) {
                    Map<String, String> userSession = new HashMap<>();
                    String activityJson = mapper.writeValueAsString(applications.get(i));
                    String timestamp = JsonPathHelper.findJsonpathList(userActivityJson, "$..userSessions[" + i + "].startTime").toString();
                    List<String> data = JsonPathHelper.findJsonpathList(activityJson, "$..data.*");
                    String activityType = data.get(0);
                    String applicationid = data.get(1);
                    String username = data.get(2);
                    String applicationtokenid = data.get(3);
                    // data.add("timestamp");
                    timestamp = timestamp.substring(1, timestamp.length() - 1);
                    c.setTimeInMillis(Long.parseLong(timestamp));
                    userSession.put("username", username);
                    userSession.put("applicationid", applicationid);
                    userSession.put("activityType", activityType);
                    userSession.put("timestamp", dateFormat.format(c.getTime()));
                    userSession.put("applicationtokenid", applicationtokenid);

                    userSessions.add(userSession);
                    i++;
                }
                return mapper.writeValueAsString(userSessions);
            }
        } catch (Exception e) {
            log.warn("Could not convert getDataElementsFromUserActivityJson Json}");
        }

        return null;
    }
}
