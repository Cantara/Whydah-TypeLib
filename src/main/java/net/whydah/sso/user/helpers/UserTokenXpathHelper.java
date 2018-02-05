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
		
		return new XpathHelper(userTokenXml).findNullableValue("/usertoken/@id");
	}


	
	public static String getRealName(String userTokenXml) {
		
		return getFirstName(userTokenXml) + " " + getLastName(userTokenXml);
	}


	public static String getFirstName(String userTokenXml) {
	

		String expression = "/usertoken/firstname";
		XpathHelper x = new XpathHelper(userTokenXml);
		String firstName = x.findNullableValue(expression);
		if (firstName != null && firstName.length() > 0) {
			log.debug("getFirstName - usertoken" + userTokenXml + "\nvalue:" + firstName);
			return firstName;
		}
		expression = "/usertoken/firstName";
		firstName = x.findNullableValue(expression);
		if (firstName != null && firstName.length() > 0) {
			log.debug("getFirstName - usertoken" + userTokenXml + "\nvalue:" + firstName);
			return firstName;
		}

		return "";
	}

	public static String getLastName(String userTokenXml) {
		
		XpathHelper x = new XpathHelper(userTokenXml);
		String expression = "/usertoken/lastname";
		String lastName = x.findNullableValue(expression);
		if (lastName != null && lastName.length() > 0) {
			log.debug("getLastName - usertoken" + userTokenXml + "\nvalue:" + lastName);
			return lastName;
		}
		expression = "/usertoken/lastName";
		lastName =  x.findNullableValue(expression);

		if (lastName != null && lastName.length() > 0) {
			log.debug("getLastName - usertoken" + userTokenXml + "\nvalue:" + lastName);
			return lastName;
		}

		return "";
	}


	public static String getUserName(String userTokenXml) {
		
		XpathHelper x = new XpathHelper(userTokenXml);
		String expression = "/usertoken/username";
		String value = x.findNullableValue(expression);
		if (value != null && value.length() > 0) {
			return value;
		}

		expression = "/usertoken/userName";
		value = x.findNullableValue(expression);
		if (value != null && value.length() > 0) {
			return value;
		} else {
			return "";
		}



	}

	public static String getUserID(String userTokenXml) {
		return new XpathHelper(userTokenXml).findNullableValue("/usertoken/uid");
	}

	public static String getPhoneNumber(String userTokenXml) {
		
		
		XpathHelper x = new XpathHelper(userTokenXml);
		String expression = "/usertoken/cellphone";
		String phoneNumber =x.findNullableValue(expression);
		if (phoneNumber != null && phoneNumber.length() > 0 ) {
			return phoneNumber;
		}
		expression = "/usertoken/cellPhone";
		phoneNumber = x.findNullableValue(expression);
		return phoneNumber==null || phoneNumber.length()==0? "":phoneNumber;
		

	}

	public static String getEmail(String userTokenXml) {		
		String email = new XpathHelper(userTokenXml).findNullableValue("/usertoken/email");
		return email==null || email.length()==0? "":email;
	}

	public static String getPersonref(String userTokenXml) {		

		XpathHelper x = new XpathHelper(userTokenXml);
		String result = x.findNullableValue("/usertoken/personRef");
		if(result==null || result.length()==0){
			result = x.findNullableValue("/usertoken/personref");
		}
		return result==null || result.length()==0 ? "":result;

	}

	public static Long getLifespan(String userTokenXml) {		
		return Long.parseLong(new XpathHelper(userTokenXml).findNullableValue("/usertoken/lifespan"));
	}

	public static Long getTimestamp(String userTokenXml) {

		return Long.parseLong(new XpathHelper(userTokenXml).findNullableValue("/usertoken/timestamp"));

	}


	public static String getSecurityLevelAsString(String userTokenXml) {

		XpathHelper x = new XpathHelper(userTokenXml);
		String expression = "/usertoken/securitylevel";
		String securityLevel= x.findNullableValue(expression);
		if(securityLevel==null || securityLevel.length()==0){
			expression = "/usertoken/securityLevel";
			return x.findNullableValue(expression);
		} else {
			return securityLevel;
		}

	}


	public static Long getSecurityLevel(String userTokenXml) {

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

		XpathHelper x = new XpathHelper(userTokenXml);
		String result = x.findNullableValue("/usertoken/lastseen");
		if(result==null || result.length()==0){
			result = x.findNullableValue("/usertoken/lastSeen");
		}
		return result==null || result.length()==0 ? "":result;
	}

	public static String getDEFCONLevel(String userTokenXml) {

		String defcon =  new XpathHelper(userTokenXml).findNullableValue("/usertoken/DEFCON");
		return defcon==null || defcon.length()==0? "":defcon;

	}
}
