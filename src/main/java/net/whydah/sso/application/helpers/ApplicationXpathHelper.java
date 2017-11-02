package net.whydah.sso.application.helpers;

import net.whydah.sso.basehelpers.XpathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

import static net.whydah.sso.application.mappers.ApplicationTokenMapper.isSane;

public class ApplicationXpathHelper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationXpathHelper.class);

    public static String getAppTokenIdFromAppToken(String appTokenXML) {

        if (!isSane(appTokenXML)) {
            log.warn(" XML injection detected - called with appTokenXML:{} - Returning null", appTokenXML);
            return null;
        }

        //log.trace("appTokenXML: {}", appTokenXML);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(appTokenXML)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/applicationtoken/params/applicationtokenID[1]";
            XPathExpression xPathExpression = xPath.compile(expression);
            String appId = xPathExpression.evaluate(doc);
            log.debug("getAppTokenIdFromAppToken: applicationTokenId={}, appTokenXML={}", appId, appTokenXML);
            return appId;
        } catch (Exception e) {
            log.error("getAppTokenIdFromAppToken - appTokenXML - Could not get applicationID from XML: " + appTokenXML, e);
        }
        return "";
    }



    public static  String getAppTokenIdFromAppTokenXml(String appTokenXML) {
        String appTokenId = "";
        if (isEmpty(appTokenXML)) {
            log.debug("appTokenXML was empty, so returning empty appTokenId.");
        } else {
            String expression = "/applicationtoken/params/applicationtokenID[1]";
            
            appTokenId = new XpathHelper(appTokenXML).findValue(expression);
        }
        return appTokenId;
    }

    public static String getAppNameFromAppTokenXml(String appTokenXML) {
        String appTokenId = "";
        if (isEmpty(appTokenXML)) {
            log.debug("roleXml was empty, so returning empty orgName.");
        } else {
            String expression = "/applicationtoken/params/applicationname[1]";
            appTokenId = new XpathHelper(appTokenXML).findValue(expression);
        }
        return appTokenId;
    }

    public static  Long getExpiresFromAppTokenXml(String appTokenXML) {
        String expiresValue = "";
        Long expires = -1L;
        if (isEmpty(appTokenXML)) {
            log.debug("appTokenXml was empty, so returning empty expires.");
        } else {
            String expression = "/applicationtoken/params/expires";
            expiresValue = new XpathHelper(appTokenXML).findValue(expression);
            try {
               expires = new Long(expiresValue);
            } catch (NumberFormatException nfe) {
                log.warn("Failed to parse Long value from expires {}, in AppToken {}", expiresValue, appTokenXML);
            }
        }
        return expires;
    }

    private static boolean isEmpty(String appTokenXML) {
        return appTokenXML == null || appTokenXML.isEmpty();
    }





}
