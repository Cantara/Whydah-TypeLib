package net.whydah.sso.user.helpers;

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

import static net.whydah.sso.user.mappers.UserTokenMapper.isSane;

public class UserTokenXpathHelper {
    private static final Logger log = LoggerFactory.getLogger(UserTokenXpathHelper.class);

    public static String getUserTokenId(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty userTokenId.");
            return "";
        }

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }


//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/@id";
//            XPathExpression xPathExpression = xPath.compile(expression);
//            return (xPathExpression.evaluate(doc));
//        } catch (Exception e) {
//            log.error("getUserTokenId parsing error", e);
//        }
//        return "";
        
        return new XpathHelper(userTokenXml).findValue("/usertoken/@id");
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

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
           

            String expression = "/usertoken/firstname";
            XpathHelper x = new XpathHelper(userTokenXml);
            String firstName = x.findValue(expression);
            if (firstName != null && firstName.length() > 0) {
                log.debug("getFirstName - usertoken" + userTokenXml + "\nvalue:" + firstName);
                return firstName;
            }
            expression = "/usertoken/firstName";
            firstName = x.findValue(expression);
            if (firstName != null && firstName.length() > 0) {
                log.debug("getFirstName - usertoken" + userTokenXml + "\nvalue:" + firstName);
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

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
            
            XpathHelper x = new XpathHelper(userTokenXml);
            String expression = "/usertoken/lastname";
            String lastName = x.findValue(expression);
            if (lastName != null && lastName.length() > 0) {
                log.debug("getLastName - usertoken" + userTokenXml + "\nvalue:" + lastName);
                return lastName;
            }
            expression = "/usertoken/lastName";
            lastName =  x.findValue(expression);

            if (lastName != null && lastName.length() > 0) {
                log.debug("getLastName - usertoken" + userTokenXml + "\nvalue:" + lastName);
                return lastName;
            }
           
        } catch (Exception e) {
            log.error("getLastName - userTokenXml parsing error", e);
        }
        return "";
    }


    public static String getUserName(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty user id");
            return "";
        }

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
            
        	XpathHelper x = new XpathHelper(userTokenXml);
            String expression = "/usertoken/username";
            String value = x.findValue(expression);
            if (value != null && value.length() > 0) {
                return value;
            }

            expression = "/usertoken/userName";
            value = x.findValue(expression);
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

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/uid";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            return xPathExpression.evaluate(doc);
//
//        } catch (Exception e) {
//            log.error("getUserID - userTokenXml", e);
//        }
        
        
        return new XpathHelper(userTokenXml).findValue("/usertoken/uid");

    }

    public static String getPhoneNumber(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty phone number");
            return "";
        }

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
            XpathHelper x = new XpathHelper(userTokenXml);
            String expression = "/usertoken/cellphone";
            String phoneNumber =x.findValue(expression);
            if (phoneNumber != null && phoneNumber.length() > 0 ) {
                return phoneNumber;
            }
            expression = "/usertoken/cellPhone";
            return x.findValue(expression);

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

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/email";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            return xPathExpression.evaluate(doc);
        	return new XpathHelper(userTokenXml).findValue("/usertoken/email");

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

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }
//
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/personRef";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            String value = xPathExpression.evaluate(doc);
//            if (value != null && value.length() > 0) {
//                return value;
//
//            }
//        	
//        } catch (Exception e) {
//            log.error("personRef missed - trying personRef");
//        }
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/personref";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            return xPathExpression.evaluate(doc);
//
//        } catch (Exception e) {
//            log.error("personref missed too, returning empty");
//        }
//        return "";
        
        XpathHelper x = new XpathHelper(userTokenXml);
    	String result = x.findValue("/usertoken/personRef");
    	if(result==null || result.length()==0){
    		result = x.findValue("/usertoken/personref");
    	}
    	return result;

    }

    public static Integer getLifespan(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty lifespan.");
            return null;
        }

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/lifespan";
//            XPathExpression xPathExpression = xPath.compile(expression);
//            return Integer.parseInt(xPathExpression.evaluate(doc));
//        } catch (Exception e) {
//            log.error("getLifespan - userTokenXml lifespan parsing error", e);
//        }
        try{
        	return Integer.parseInt(new XpathHelper(userTokenXml).findValue("/usertoken/lifespan"));
        }catch(Exception e){
        	 log.error("getLifespan - userTokenXml lifespan parsing error", e);
        	 return null;
        }
    }

    public static Long getTimestamp(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty timestamp.");
            return null;
        }

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/timestamp";
//            XPathExpression xPathExpression = xPath.compile(expression);
//            log.debug("token" + userTokenXml + "\nvalue:" + xPathExpression.evaluate(doc));
//            return Long.parseLong(xPathExpression.evaluate(doc));
        	
        	return Long.parseLong(new XpathHelper(userTokenXml).findValue("/usertoken/timestamp"));
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

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/securitylevel";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            String securityLevel = xPathExpression.evaluate(doc);
//            if (securityLevel != null && securityLevel.length() > 0) {
//                log.debug("token" + userTokenXml + "\nvalue:" + securityLevel);
//                return securityLevel;
//            }
//
//            expression = "/usertoken/securityLevel";
//            xPathExpression = xPath.compile(expression);
//
//            securityLevel = xPathExpression.evaluate(doc);
//            if (securityLevel != null && securityLevel.length() > 0) {
//                log.debug("token" + userTokenXml + "\nvalue:" + securityLevel);
//                return securityLevel;
//            }

        	XpathHelper x = new XpathHelper(userTokenXml);
        	String expression = "/usertoken/securitylevel";
        	String securityLevel= x.findValue(expression);
        	if(securityLevel==null){
        		expression = "/usertoken/securityLevel";
        		return x.findValue(expression);
        	} else {
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

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/lastseen";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            String lastSeen = xPathExpression.evaluate(doc);
//            if (lastSeen != null && lastSeen.length() > 0) {
//                log.debug("token" + userTokenXml + "\nvalue:" + lastSeen);
//                return lastSeen;
//            }
//
//            expression = "/usertoken/lastSeen";
//            xPathExpression = xPath.compile(expression);
//
//            lastSeen = xPathExpression.evaluate(doc);
//            if (lastSeen != null && lastSeen.length() > 0) {
//                log.debug("token" + userTokenXml + "\nvalue:" + lastSeen);
//                return lastSeen;
//            }
        	
        	XpathHelper x = new XpathHelper(userTokenXml);
        	String result = x.findValue("/usertoken/lastseen");
        	if(result==null || result.length()==0){
        		result = x.findValue("/usertoken/lastSeen");
        	}
        	return result;

        } catch (Exception e) {
            log.error("getLastSeen - userTokenXml lastSeen parsing error", e);
        }
        return null;
    }

    public static String getDEFCONLevel(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty emailr");
            return "";
        }

        if (!isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new InputSource(new StringReader(userTokenXml)));
//            XPath xPath = XPathFactory.newInstance().newXPath();
//
//            String expression = "/usertoken/DEFCON";
//            XPathExpression xPathExpression = xPath.compile(expression);
//
//            return xPathExpression.evaluate(doc);
        	return new XpathHelper(userTokenXml).findValue("/usertoken/DEFCON");

        } catch (Exception e) {
            log.error("getDefConLevel - userTokenXml", e);
        }
        return "";

    }
}
