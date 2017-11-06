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
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.StringReader;

import static net.whydah.sso.application.mappers.ApplicationTokenMapper.isSane;

public class ApplicationXpathHelper {
	private static final Logger log = LoggerFactory.getLogger(ApplicationXpathHelper.class);

	public static String getAppTokenIdFromAppToken(String appTokenXML) {  
		try {
			return new XpathHelper(appTokenXML).findValue("/applicationtoken/params/applicationtokenID[1]");
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
			return xPath.findValue(expression);
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
			return xPath.findValue(expression);
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
			expires = new Long(expiresValue);
		} catch (NumberFormatException nfe) {
			log.warn("Failed to parse Long value from expires {}, in AppToken {}", expiresValue, appTokenXML);
		}

		return expires;
	}






}
