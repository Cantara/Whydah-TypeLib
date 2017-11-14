package net.whydah.sso.user.mappers;

import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.user.types.UserCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCredentialMapper {

    public static final Logger log = LoggerFactory.getLogger(UserCredentialMapper.class);


    public static UserCredential fromXml(String userCredentialXml) {
     
       

    	XpathHelper xPath = new XpathHelper(userCredentialXml);
    	if (!xPath.isValid()) {
    		return null;
    	}

    	String password = (String) xPath.findNullableValue("/usercredential/params/password");
    	String userName = (String) xPath.findNullableValue("/usercredential/params/username");

    	UserCredential userCredential = new UserCredential(userName, password);
    	return userCredential;	
    	
            
     
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