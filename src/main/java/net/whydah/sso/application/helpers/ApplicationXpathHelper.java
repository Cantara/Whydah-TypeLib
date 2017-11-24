package net.whydah.sso.application.helpers;

import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.ddd.model.application.ApplicationName;
import net.whydah.sso.ddd.model.application.ApplicationTokenExpires;
import net.whydah.sso.ddd.model.application.ApplicationTokenID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.xpath.XPathExpressionException;

public class ApplicationXpathHelper {
	private static final Logger log = LoggerFactory.getLogger(ApplicationXpathHelper.class);

	public static String getAppTokenIdFromAppToken(String appTokenXML) {  
		try {
            String applicationTokenId = new XpathHelper(appTokenXML).findValue("/applicationtoken/params/applicationtokenID[1]");
            if (ApplicationTokenID.isValid(applicationTokenId)) {
                return applicationTokenId;
            }
            return null;
        } catch (XPathExpressionException e) {
			return null;
		}
	}



	public static  String getAppTokenIdFromAppTokenXml(String appTokenXML) {

		String expression = "/applicationtoken/params/applicationtokenID[1]";
		try {
			XpathHelper xPath =  new XpathHelper(appTokenXML);
			if(!xPath.isValid()){
				return "";
			}
            String applicationTokenId = xPath.findValue(expression);
            if (ApplicationTokenID.isValid(applicationTokenId)) {
                return applicationTokenId;
            }
            return "";
        } catch (XPathExpressionException e) {
			return "";
		}

	}

	public static String getAppNameFromAppTokenXml(String appTokenXML) {

		String expression = "/applicationtoken/params/applicationname[1]";
		try {
			XpathHelper xPath =  new XpathHelper(appTokenXML);
			if(!xPath.isValid()){
				return "";
			}
            String applicationName = xPath.findValue(expression);
            if (ApplicationName.isValid(applicationName)) {
                return applicationName;
            }
            return "";
        } catch (XPathExpressionException e) {
			return "";
		}

	}

	public static  Long getExpiresFromAppTokenXml(String appTokenXML) {
		String expiresValue = "";
		Long expires = -1L;

		String expression = "/applicationtoken/params/expires";
		try {
			expiresValue = new XpathHelper(appTokenXML).findValue(expression);
		} catch (XPathExpressionException e) {
			return expires;
		}

		try {
            expires = new ApplicationTokenExpires(expiresValue).getValue();
        } catch (NumberFormatException nfe) {
			log.warn("Failed to parse Long value from expires {}, in AppToken {}", expiresValue, appTokenXML);
		}

		return expires;
	}






}
