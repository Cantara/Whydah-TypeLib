package net.whydah.sso.extensions.useractivity.helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.whydah.TimeLimitedCodeBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UserActivityHelper {

    /**
     * {
     * "prefix":"All",
     * "activityName":"userSession",
     * "startTime":1497809993907,
     * "endTime":1497896393907,
     * "activities":{
     * "userSessions":[
     * {
     * "prefix":"",
     * "name":"userSession",
     * "startTime":1497811363000,
     * "data":{
     * "usersessionfunction":"userSessionVerification",
     * "applicationid":"2215",
     * "userid":"useradmin",
     * "applicationtokenid":"2ce9e54779be0b9dbce1623d1847fe93"
     * }
     * },
     * {
     * "prefix":"",
     * "name":"userSession",
     * "startTime":1497811363000,
     * "data":{
     * "usersessionfunction":"userSessionVerification",
     * "applicationid":"2215",
     * "userid":"useradmin",
     * "applicationtokenid":"2ce9e54779be0b9dbce1623d1847fe93"
     * }
     * }
     * ]
     * }
     * }
     */
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
        return getUserSessionsJsonFromUserActivityJson(userActivityJson, "");
    }

    public static String getTimedUserSessionsJsonFromUserActivityJson(String userActivityJson, String filterusername) {
        final long startTime = System.currentTimeMillis();
        log(startTime, "calling runWithTimeout!");
        String result = "";
        try {
            result = TimeLimitedCodeBlock.runWithTimeout(new Callable<String>() {
                @Override
                public String call() {
//                    try {
                    log(startTime, "starting sleep!");
                    String r = getUserSessionsJsonFromUserActivityJson(userActivityJson, filterusername);
                    log(startTime, "woke up!");
                    return r;

                    //throw new InterruptedException("");
                    //                  }
                    //                  catch (InterruptedException e) {
                    //                      log(startTime, "was interrupted!");
                    //                  }
                }
            }, 30, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log(startTime, "got timeout!");
        } catch (Exception e) {
            log(startTime, "got exception!");
        }
        log(startTime, "end of main method!");
        return result;
    }

    public static String getUserSessionsJsonFromUserActivityJson(String userActivityJson, String filteruserid) {
        try {
            if (userActivityJson == null) {
                log.trace("getDataElementsFromUserActivityJson was empty, so returning null.");
            } else {
                List<String> applications = JsonPathHelper.findJsonpathList(userActivityJson, "$..userSessions.*");
                if (applications == null) {
                    log.debug("jsonpath returned zero hits");
                    return null;
                }
                Map<String, Map<String, String>> userSessions = new HashMap<String, Map<String, String>>();

                final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                Calendar c = new GregorianCalendar();

                int i = 0;
           
                while (i < applications.size()) {
                    Map<String, String> userSession = new HashMap<>();
                    String activityJson = mapper.writeValueAsString(applications.get(applications.size() - i - 1));
                    String timestamp = JsonPathHelper.findJsonpathList(activityJson, "$..startTime").toString();
                    List<String> data = JsonPathHelper.findJsonpathList(activityJson, "$..data.*");
                    String usersessionfunction = data.get(0);
                    String applicationid = data.get(1);
                    String userid = data.get(2);
                    String applicationtokenid = data.get(3);
                    // data.add("timestamp");
                    timestamp = timestamp.substring(1, timestamp.length() - 1);
                    c.setTimeInMillis(Long.parseLong(timestamp));
                    if (filteruserid == null || filteruserid.length() < 1 || filteruserid.equalsIgnoreCase(userid)) {
                    	 if (!userSessions.containsKey(applicationid + usersessionfunction + userid + applicationtokenid)) {
                        	 userSession.put("userid", userid);
                             userSession.put("applicationid", applicationid);
                             userSession.put("usersessionfunction", usersessionfunction);
                             userSession.put("timestamp", dateFormat.format(c.getTime()));
                             userSession.put("applicationtokenid", applicationtokenid);
                             userSession.put("activity_count", String.valueOf(1));
                             userSessions.put(applicationid + usersessionfunction + userid + applicationtokenid, userSession);
                        } else {
                        	 userSession = userSessions.get(applicationid + usersessionfunction + userid + applicationtokenid);
                        	 userSession.put("activity_count", String.valueOf(Integer.valueOf(userSession.get("activity_count"))+1));
                        	 userSession.put("timestamp", userSession.get("timestamp") + ", " + dateFormat.format(c.getTime()));
                        }
                        
                       
                    }
                    i++;
                }
                return mapper.writeValueAsString(userSessions.values());
            }
        } catch (Exception e) {
            log.warn("Could not convert getDataElementsFromUserActivityJson Json}");
        }

        return null;
    }


    public static String getTimedUserSessionsJsonFromUserActivityJson(String userActivityJson, String filterusername, String filterAppId) {
        final long startTime = System.currentTimeMillis();
        log(startTime, "calling runWithTimeout!");
        String result = "";
        try {
            result = TimeLimitedCodeBlock.runWithTimeout(new Callable<String>() {
                @Override
                public String call() {
//                    try {
                    log(startTime, "starting sleep!");
                    String r = getUserSessionsJsonFromUserActivityJson(userActivityJson, filterusername, filterAppId);
                    log(startTime, "woke up!");
                    return r;

                    //throw new InterruptedException("");
                    //                  }
                    //                  catch (InterruptedException e) {
                    //                      log(startTime, "was interrupted!");
                    //                  }
                }
            }, 30, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log(startTime, "got timeout!");
        } catch (Exception e) {
            log(startTime, "got exception!");
        }
        log(startTime, "end of main method!");
        return result;
    }

    private static void log(long startTime, String msg) {
        long elapsedSeconds = (System.currentTimeMillis() - startTime);
        log.trace("%1$5sms [%2$16s] %3$s\n", elapsedSeconds, Thread.currentThread().getName(), msg);
    }


    public static String getUserSessionsJsonFromUserActivityJson(String userActivityJson, String filteruserid, String filterAppId) {
        try {
            if (userActivityJson == null) {
                log.trace("getDataElementsFromUserActivityJson was empty, so returning null.");
            } else {
                List<String> applications = JsonPathHelper.findJsonpathList(userActivityJson, "$..userSessions.*");
                if (applications == null) {
                    log.debug("jsonpath returned zero hits");
                    return null;
                }
                Map<String, Map<String, String>> userSessions = new HashMap<String, Map<String, String>>();

                final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                Calendar c = new GregorianCalendar();

                int i = 0;
             
                while (i < applications.size()) {
                    Map<String, String> userSession = new HashMap<>();
                    String activityJson = mapper.writeValueAsString(applications.get(applications.size() - i - 1));
                    String timestamp = JsonPathHelper.findJsonpathList(activityJson, "$..startTime").toString();
                    List<String> data = JsonPathHelper.findJsonpathList(activityJson, "$..data.*");
                    
                    String usersessionfunction = data.get(0);
                    String applicationid = data.get(1);
                    String userid = data.get(2);
                    String applicationtokenid = data.get(3);
               
                   
                    timestamp = timestamp.substring(1, timestamp.length() - 1);
                    c.setTimeInMillis(Long.parseLong(timestamp));
                    if ((filteruserid == null || filteruserid.length() < 1 || filteruserid.equalsIgnoreCase(userid)) && applicationid.equals(filterAppId)) {
                        if (!userSessions.containsKey(applicationid + usersessionfunction + userid + applicationtokenid)) {
                        	 userSession.put("userid", userid);
                             userSession.put("applicationid", applicationid);
                             userSession.put("usersessionfunction", usersessionfunction);
                             userSession.put("timestamp", dateFormat.format(c.getTime()));
                             userSession.put("applicationtokenid", applicationtokenid);
                             userSession.put("activity_count", String.valueOf(1));
                             userSessions.put(applicationid + usersessionfunction + userid + applicationtokenid, userSession);
                        } else {
                        	 userSession = userSessions.get(applicationid + usersessionfunction + userid + applicationtokenid);
                        	 userSession.put("activity_count", String.valueOf(Integer.valueOf(userSession.get("activity_count"))+1));
                        	 userSession.put("timestamp", userSession.get("timestamp") + ", " + dateFormat.format(c.getTime()));
                        }
                        
                       
                    }
                    i++;
                }
                return mapper.writeValueAsString(userSessions.values());
            }
        } catch (Exception e) {
            log.warn("Could not convert getDataElementsFromUserActivityJson Json}");
        }

        return null;
    }
}
