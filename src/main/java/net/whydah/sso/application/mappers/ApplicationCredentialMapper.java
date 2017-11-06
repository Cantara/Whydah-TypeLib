package net.whydah.sso.application.mappers;

import java.io.InputStream;

import javax.xml.xpath.XPathExpressionException;

import net.whydah.sso.application.types.ApplicationCredential;
import net.whydah.sso.basehelpers.XpathHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        	String applicationName = xPath.findNullableValue("//applicationName");
        	if(applicationName==null){
        		applicationName ="";
        	}
        	String applicationSecret = xPath.findValue("//applicationSecret");
        	String applicationurl = xPath.findNullableValue("//applicationurl");
            String minimumsecuritylevel = xPath.findNullableValue("//minimumsecuritylevel");
            
            if(applicationurl!=null && minimumsecuritylevel!=null){
            	return new ApplicationCredential(applicationId, applicationName, applicationSecret, applicationurl, minimumsecuritylevel);
            } else {
            	return new ApplicationCredential(applicationId, applicationName, applicationSecret);
            } 
            
        } catch(XPathExpressionException ex){
        	return null;
        }
       
       

    }
   
  

}
