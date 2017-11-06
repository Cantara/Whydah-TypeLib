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



public class UserXpathHelper {
    private static final Logger log = LoggerFactory.getLogger(UserXpathHelper.class);


    /**
     * UserTokenXml parsers
     *
     */
    public static boolean hasRoleFromUserToken(String userTokenXml, String applicationId, String roleName) {
        String userRole = "";

        String expression = "count(/usertoken/application[@ID='"+applicationId+"']/role[@name='"+roleName+"'])";         
        userRole = new XpathHelper(userTokenXml).findNullableValue(expression);
        return !(userRole == null || userRole.length()==0 ||"0".equalsIgnoreCase(userRole));

        
    }

    public static String getRoleValueFromUserToken(String userTokenXml, String applicationId, String roleName) {

    	String expression = "count(/usertoken/application[@ID='"+applicationId+"']/role[@name='"+roleName+"'])";
    	String userRole = new XpathHelper(userTokenXml).findNullableValue(expression);
    	if (userRole==null || userRole.length()==0 || "0".equalsIgnoreCase(userRole)){
    		return null;
    	}
    	return new XpathHelper(userTokenXml).findNullableValue("/usertoken/application[@ID='" + applicationId + "']/role[@name='" + roleName + "']");		

    }

    public static String getRoleNamesFromUserToken(String userTokenXml) {
        return new XpathHelper(userTokenXml).findNullableValue("/usertoken/*/role/@name");
    }


    public static String getUserTokenId(String userTokenXml) {
        String expression = "/usertoken/@id";
        return new XpathHelper(userTokenXml).findNullableValue(expression);
       
    }
    public static String getUserIdFromUserTokenXml(String userTokenXml) {
       
    	String expression = "/usertoken/uid";
    	return new XpathHelper(userTokenXml).findNullableValue(expression);
    }

    public static String getRealNameFromUserTokenXml(String userTokenXml){
      
    	XpathHelper x = new XpathHelper(userTokenXml);
    	String fn = x.findNullableValue( "/usertoken/firstname");
    	if(fn==null){
    		fn = "";
    	}
    	String ln = x.findNullableValue( "/usertoken/lastname");
    	if(ln==null){
    		ln ="";
    	}
    	return fn + " " + ln;
    }


    public static Long getLifespanFromUserTokenXml(String userTokenXml) {
      
    	String value = new XpathHelper(userTokenXml).findNullableValue("/usertoken/lifespan");
    	if ((value == null) || (value.length() == 0)) {
    		return 0L;  // Return 0 on empty tags
    	}
    	return Long.parseLong(value);

    }

    public static Long getTimestampFromUserTokenXml(String userTokenXml) {
       
    	String value =  new XpathHelper(userTokenXml).findNullableValue("/usertoken/timestamp");
    	if ((value == null) || (value.length() == 0)) {
    		return 0L;  // Return 0 on empty tags
    	}
    	return Long.parseLong(value);

    }


    /**
     * UserIdentityXml parsers
     *
     */
    public static String getUserNameFromUserIdentityXml(String userIdentityXml) {
        String userName = "";  
        String expression = "/whydahuser/identity/username";
        userName =  new XpathHelper(userIdentityXml).findNullableValue(expression);
        return userName==null || userName.length()==0? "":userName;
    }
    public static String getUserIdFromUserIdentityXml(String userIdentityXml) {
        String expression = "/whydahuser/identity/UID";
        return new XpathHelper(userIdentityXml).findNullableValue(expression);
     
    }




    public static String getUserNameFromUserTokenXml(String userAdminTokenXml) {

        String expresseion = "usertoken/username";
        String userName = new XpathHelper(userAdminTokenXml).findNullableValue(expresseion);
        return userName==null || userName.length()==0? "":userName;
    }

}
