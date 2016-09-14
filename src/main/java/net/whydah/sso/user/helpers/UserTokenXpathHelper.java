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
        return getFirstName(userTokenXml) + " " + getLastName(userTokenXml);
    }


    public static String getFirstName(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty firstName.");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/firstname";
            XPathExpression xPathExpression = xPath.compile(expression);
            String firstName = xPathExpression.evaluate(doc);
            if (firstName != null && firstName.length() > 0) {
                log.debug("getRealName - usertoken" + userTokenXml + "\nvalue:" + firstName);
                return firstName;
            }
            expression = "/usertoken/firstName";
            xPathExpression = xPath.compile(expression);
            firstName = xPathExpression.evaluate(doc);

            if (firstName != null && firstName.length() > 0) {
                log.debug("getRealName - usertoken" + userTokenXml + "\nvalue:" + firstName);
                return firstName;
            }
        } catch (Exception e) {
            log.error("getFirstName - userTokenXml parsing error", e);
        }
        return "";
    }

    public static String getLastName(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty firstName.");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/lastname";
            XPathExpression xPathExpression = xPath.compile(expression);
            String lastName = xPathExpression.evaluate(doc);
            if (lastName != null && lastName.length() > 0) {
                log.debug("getLastName - usertoken" + userTokenXml + "\nvalue:" + lastName);
                return lastName;
            }
            expression = "/usertoken/lastName";
            xPathExpression = xPath.compile(expression);
            lastName = xPathExpression.evaluate(doc);

            if (lastName != null && lastName.length() > 0) {
                log.debug("getRealName - usertoken" + userTokenXml + "\nvalue:" + lastName);
                return lastName;
            }
        } catch (Exception e) {
            log.error("getFirstName - userTokenXml parsing error", e);
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

            String phoneNumber = xPathExpression.evaluate(doc);
            if (phoneNumber != null && phoneNumber.length() > 0 ) {
                return phoneNumber;
            }

            expression = "/usertoken/cellPhone";
            xPathExpression = xPath.compile(expression);

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


    public static String getSecurityLevelAsString(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty securityLevel.");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/securitylevel";
            XPathExpression xPathExpression = xPath.compile(expression);

            String securityLevel = xPathExpression.evaluate(doc);
            if (securityLevel != null && securityLevel.length() > 0) {
                log.debug("token" + userTokenXml + "\nvalue:" + securityLevel);
                return securityLevel;
            }

            expression = "/usertoken/securityLevel";
            xPathExpression = xPath.compile(expression);

            securityLevel = xPathExpression.evaluate(doc);
            if (securityLevel != null && securityLevel.length() > 0) {
                log.debug("token" + userTokenXml + "\nvalue:" + securityLevel);
                return securityLevel;
            }

        } catch (Exception e) {
            log.error("getSecurityLevel - userTokenXml securityLevel parsing error", e);
        }
        return null;
    }


    public static Long getSecurityLevel(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty securityLevel.");
            return null;
        }
        try {
            String securityLevel = getSecurityLevelAsString(userTokenXml);
            if (securityLevel != null && securityLevel.length() > 0) {
                return Long.parseLong(securityLevel);
            }
        } catch (Exception e) {
            log.error("getSecurityLevel - userTokenXml securityLevel parsing error", e);
        }
        return null;
    }


    public static String getLastSeen(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty last seen.");
            return "";
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/usertoken/lastseen";
            XPathExpression xPathExpression = xPath.compile(expression);

            String lastSeen = xPathExpression.evaluate(doc);
            if (lastSeen != null && lastSeen.length() > 0) {
                log.debug("token" + userTokenXml + "\nvalue:" + lastSeen);
                return lastSeen;
            }

            expression = "/usertoken/lastSeen";
            xPathExpression = xPath.compile(expression);

            lastSeen = xPathExpression.evaluate(doc);
            if (lastSeen != null && lastSeen.length() > 0) {
                log.debug("token" + userTokenXml + "\nvalue:" + lastSeen);
                return lastSeen;
            }

        } catch (Exception e) {
            log.error("getSecurityLevel - userTokenXml lastSeen parsing error", e);
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
