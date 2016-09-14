package net.whydah.sso.user.helpers;

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

public class UserTokenXpathHelper {
    private static final Logger log = LoggerFactory.getLogger(UserTokenXpathHelper.class);

    public static String getUserTokenId(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty userTokenId.");
            return "";
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/@id";
            XPathExpression xPathExpression = xPath.compile(expression);
            return (xPathExpression.evaluate(doc));
        } catch (Exception e) {
            log.error("getUserTokenId parsing error", e);
        }
        return "";
    }


    public static String getAppTokenIdFromAppToken(String appTokenXML) {
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

    public static String getRealName(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty realName.");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/firstname";
            XPathExpression xPathExpression = xPath.compile(expression);
            String expression2 = "/usertoken/lastname";
            XPathExpression xPathExpression2 = xPath.compile(expression2);
            String realName = xPathExpression.evaluate(doc) + " " + xPathExpression2.evaluate(doc);
            log.debug("getRealName - usertoken" + userTokenXml + "\nvalue:" + realName);
            return realName;
        } catch (Exception e) {
            log.error("getRealName - userTokenXml - getTimestamp parsing error", e);
        }
        return "";
    }

    public static String getUserName(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty user id");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/username";
            XPathExpression xPathExpression = xPath.compile(expression);

            String value = xPathExpression.evaluate(doc);
            if (value != null && value.length() > 0) {
                return value;

            }

            expression = "/usertoken/userName";
            xPathExpression = xPath.compile(expression);
            value = xPathExpression.evaluate(doc);
            if (value != null && value.length() > 0) {
                return value;

            }


        } catch (Exception e) {
            log.error("getUserName - userTokenXml", e);
        }
        return "";

    }

    public static String getUserID(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty user id");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/uid";
            XPathExpression xPathExpression = xPath.compile(expression);

            return xPathExpression.evaluate(doc);

        } catch (Exception e) {
            log.error("getUserID - userTokenXml", e);
        }
        return "";

    }

    public static String getPhoneNumber(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty phone number");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/cellphone";
            XPathExpression xPathExpression = xPath.compile(expression);

            return xPathExpression.evaluate(doc);

        } catch (Exception e) {
            log.error("getPhoneNumber - userTokenXml", e);
        }
        return "";

    }

    public static String getEmail(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty email");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/email";
            XPathExpression xPathExpression = xPath.compile(expression);

            return xPathExpression.evaluate(doc);

        } catch (Exception e) {
            log.error("email - userTokenXml", e);
        }
        return "";

    }

    public static String getPersonref(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty personref");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/personRef";
            XPathExpression xPathExpression = xPath.compile(expression);

            String value = xPathExpression.evaluate(doc);
            if (value != null && value.length() > 0) {
                return value;

            }

        } catch (Exception e) {
            log.error("personRef missed - trying personRef");
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/personref";
            XPathExpression xPathExpression = xPath.compile(expression);

            return xPathExpression.evaluate(doc);

        } catch (Exception e) {
            log.error("personref missed too, returning empty");
        }
        return "";

    }

    public static Integer getLifespan(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty lifespan.");
            return null;
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/lifespan";
            XPathExpression xPathExpression = xPath.compile(expression);
            return Integer.parseInt(xPathExpression.evaluate(doc));
        } catch (Exception e) {
            log.error("getLifespan - userTokenXml lifespan parsing error", e);
        }
        return null;
    }

    public static Long getTimestamp(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty timestamp.");
            return null;
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/timestamp";
            XPathExpression xPathExpression = xPath.compile(expression);
            log.debug("token" + userTokenXml + "\nvalue:" + xPathExpression.evaluate(doc));
            return Long.parseLong(xPathExpression.evaluate(doc));
        } catch (Exception e) {
            log.error("getTimestamp - userTokenXml timestamp parsing error", e);
        }
        return null;
    }

    public static Long getSecurityLevel(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty securityLevel.");
            return null;
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/securitylevel";
            XPathExpression xPathExpression = xPath.compile(expression);
            log.debug("token" + userTokenXml + "\nvalue:" + xPathExpression.evaluate(doc));
            return Long.parseLong(xPathExpression.evaluate(doc));
        } catch (Exception e) {
            log.error("getSecurityLevel - userTokenXml securityLevel parsing error", e);
        }
        return null;
    }


    public static String getDEFCONLevel(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty emailr");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/DEFCON";
            XPathExpression xPathExpression = xPath.compile(expression);

            return xPathExpression.evaluate(doc);

        } catch (Exception e) {
            log.error("getDefConLevel - userTokenXml", e);
        }
        return "";

    }
}
