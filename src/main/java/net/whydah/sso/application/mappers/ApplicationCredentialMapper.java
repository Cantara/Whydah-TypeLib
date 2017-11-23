package net.whydah.sso.application.mappers;

import net.whydah.sso.application.types.ApplicationCredential;
import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.ddd.model.application.ApplicationId;
import net.whydah.sso.ddd.model.application.ApplicationName;
import net.whydah.sso.ddd.model.application.ApplicationSecret;
import net.whydah.sso.ddd.model.application.ApplicationUrl;
import net.whydah.sso.ddd.model.user.SecurityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.xpath.XPathExpressionException;
import java.io.InputStream;
//import net.whydah.sso.basehelpers.Sanitizers;


public class ApplicationCredentialMapper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationCredentialMapper.class);

    public static String toXML(ApplicationCredential applicationCredential) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
                "<applicationcredential>\n" +
                "    <params>\n" +
                "        <applicationID>" + applicationCredential.getApplicationID() + "</applicationID>\n" +
                "        <applicationName>" + applicationCredential.getApplicationName() + "</applicationName>\n" +
                "        <applicationSecret>" + applicationCredential.getApplicationSecret() + "</applicationSecret>\n" +
                "        <applicationurl>" + applicationCredential.getApplicationurl() + "</applicationurl>\n" +
                "        <minimumsecuritylevel>" + applicationCredential.getMinimumsecuritylevel() + "</minimumsecuritylevel>" +
                "    </params> \n" +
                "</applicationcredential>\n";
    }

    public static ApplicationCredential fromXml(InputStream input) {
      
         return extractApplicationCredential(input.toString());
       
    }

    public static ApplicationCredential fromXml(String xml) {
      
        return extractApplicationCredential(xml);
    }

    private static ApplicationCredential extractApplicationCredential(String xmlString) {     
        XpathHelper xPath = new XpathHelper(xmlString);
        if(!xPath.isValid()){
        	return null;
        }
        try{
        	String applicationId = xPath.findValue("//applicationID");
            if (!ApplicationId.isValid(applicationId)) {
                log.warn("Old appid fallback for applicationID");
                applicationId = xPath.findValue("//appid");
            }
            String applicationName = xPath.findNullableValue("//applicationName");
            if (!ApplicationName.isValid(applicationName)) {
                applicationName = "NotFound";
            }
        	String applicationSecret = xPath.findValue("//applicationSecret");
            if (!ApplicationSecret.isValid(applicationSecret)) {
                log.warn("Old appsecret fallback for applicationSecret");
                applicationSecret = xPath.findValue("//appsecret");
            }
            String applicationurl = xPath.findNullableValue("//applicationurl");
            String minimumsecuritylevel = xPath.findNullableValue("//minimumsecuritylevel");

            if (ApplicationUrl.isValid(applicationurl) && SecurityLevel.isValid(minimumsecuritylevel)) {
                return new ApplicationCredential(applicationId, applicationName, applicationSecret, applicationurl, minimumsecuritylevel);
            } else {
            	return new ApplicationCredential(applicationId, applicationName, applicationSecret);
            } 
            
        } catch(XPathExpressionException ex){
        	return null;
        }
       
       

    }
   
  

}
