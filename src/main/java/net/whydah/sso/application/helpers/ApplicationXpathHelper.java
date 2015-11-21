package net.whydah.sso.application.helpers;

import net.whydah.sso.basehelpers.XpathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationXpathHelper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationXpathHelper.class);



    public static  String getAppTokenIdFromAppTokenXml(String appTokenXML) {
        String appTokenId = "";
        if (appTokenXML == null) {
            log.debug("roleXml was empty, so returning empty orgName.");
        } else {
            String expression = "/applicationtoken/params/applicationtokenID[1]";
            appTokenId = XpathHelper.findValue(appTokenXML, expression);
        }
        return appTokenId;
    }

    public static String getAppNameFromAppTokenXml(String appTokenXML) {
        String appTokenId = "";
        if (appTokenXML == null) {
            log.debug("roleXml was empty, so returning empty orgName.");
        } else {
            String expression = "/applicationtoken/params/applicationname[1]";
            appTokenId = XpathHelper.findValue(appTokenXML, expression);
        }
        return appTokenId;
    }

    public static  Long getExpiresFromAppTokenXml(String appTokenXML) {
        String expiresValue = "";
        Long expires = -1L;
        if (appTokenXML == null) {
            log.debug("appTokenXml was empty, so returning empty expires.");
        } else {
            String expression = "/applicationtoken/params/expires";
            expiresValue = XpathHelper.findValue(appTokenXML, expression);
            try {
               expires = new Long(expiresValue);
            } catch (NumberFormatException nfe) {
                log.warn("Failed to parse Long value from expires {}, in AppToken {}", expiresValue, appTokenXML);
            }
        }
        return expires;
    }





}
