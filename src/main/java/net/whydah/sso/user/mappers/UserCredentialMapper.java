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
     
       

    	XpathHelper xPath = new XpathHelper(userCredentialXml);
    	if (!xPath.isValid()) {
    		return null;
    	}

    	String password = (String) xPath.findNullableValue("/usercredential/params/password");
    	String userName = (String) xPath.findNullableValue("/usercredential/params/username");

    	UserCredential userCredential = new UserCredential(userName, password);
    	if(userCredential.isValid()){
    		return userCredential;	
    	} else {
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



}