package net.whydah.sso.user.mappers;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.user.types.UserCredential;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import java.io.StringReader;

public class UserCredentialMapper {

    public static final Logger log = LoggerFactory.getLogger(UserCredentialMapper.class);
    public static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    public static UserCredential fromXml(String userCredentialXml) {
        // Block XML injection to XML libraries
        if (userCredentialXml == null || !isSane(userCredentialXml)) {
            return null;
        }

        try {
       
            XpathHelper xPath = new XpathHelper(userCredentialXml);
            String password = (String) xPath.findValue("/usercredential/params/password");
            String userName = (String) xPath.findValue("/usercredential/params/username");

            UserCredential userCredential = new UserCredential(userName, password);
            if(userCredential.isValid()){
            	return userCredential;	
            } else {
            	return null;
            }
            
        } catch (Exception e) {
            log.error("Error parsing userCredentialXml " + userCredentialXml, e);
            return null;
        }
    }

    public static String toXML(UserCredential userCredential) {


        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
                "<usercredential>\n" +
                "    <params>\n" +
                "        <username>" + userCredential.getUserName() + "</username>\n" +
                "        <password>" + userCredential.getPassword() + "</password>\n" +
                "    </params> \n" +
                "</usercredential>\n";

    }

    public static String toSafeXML(UserCredential userCredential) {


        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
                "<usercredential>\n" +
                "    <params>\n" +
                "        <username>" + userCredential.getUserName() + "</username>\n" +
                "        <password>********</password>\n" +
                "    </params> \n" +
                "</usercredential>\n";

    }

    public static boolean isSane(String inputString) {
//        if (inputString == null || !(inputString.indexOf("usercredential") < 65) || inputString.length() != Sanitizers.sanitize(inputString).length()) {
//            log.trace(" - suspicious XML received, rejected.");
//            return false;
//        }
//        return true;
    	return Validator.isValidXml(inputString);
    }

}