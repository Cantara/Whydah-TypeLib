package net.whydah.sso.application.mappers;


import net.whydah.sso.application.helpers.ApplicationTokenXpathHelper;
import net.whydah.sso.application.types.ApplicationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationTokenMapper {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTokenMapper.class);


    public static String toXML(ApplicationToken applicationToken) {
        return
                " <applicationtoken>\n" +
                "     <params>\n" +
                "         <applicationtokenID>" + applicationToken.getApplicationTokenId() + "</applicationtokenID>\n" +
                "         <applicationid>" + applicationToken.getApplicationID() + "</applicationid>\n" +
                "         <applicationname>" + applicationToken.getApplicationName() + "</applicationname>\n" +
                "         <expires>" + applicationToken.getExpires() + "</expires>\n" +
                "     </params> \n" +
                "     <Url type=\"application/xml\" method=\"POST\" " +
                "                template=\"" + applicationToken.getBaseuri() + "user/" + applicationToken.getApplicationTokenId() + "/get_usertoken_by_usertokenid\"/> \n" +
                " </applicationtoken>\n";
    }


    public static ApplicationToken fromXml(String input) {
        return extractApplicationToken(input);
    }

    private static ApplicationToken extractApplicationToken(String applicationTokenXML) {
        ApplicationToken applicationToken = null;
        if (applicationTokenXML != null && !applicationTokenXML.isEmpty()) {
            applicationTokenXML = applicationTokenXML.substring(applicationTokenXML.indexOf("<applicationtoken>"));
            if (applicationTokenXML != null && !applicationTokenXML.isEmpty()) {
                applicationToken = new ApplicationToken();

                applicationToken.setApplicationTokenId(ApplicationTokenXpathHelper.getApplicationTokenIDFromApplicationToken(applicationTokenXML));
                applicationToken.setApplicationID(ApplicationTokenXpathHelper.getApplicationIDFromApplicationToken(applicationTokenXML));
                applicationToken.setApplicationName(ApplicationTokenXpathHelper.getApplicationNameFromApplicationToken(applicationTokenXML));
                applicationToken.setExpires(ApplicationTokenXpathHelper.getApplicationExpiresFromApplicationToken(applicationTokenXML));
            }
        }
        return applicationToken;
    }


    public static ApplicationToken fromApplicationCredentialXML(String xml) {
        ApplicationToken appToken = null;
        if (xml != null && !xml.isEmpty()) {
            appToken = new ApplicationToken();
            appToken.setApplicationID(ApplicationTokenXpathHelper.getApplicationIDFromApplicationCredential(xml));
            appToken.setApplicationName(ApplicationTokenXpathHelper.getApplicationNameFromApplicationCredential(xml));
            appToken.setApplicationSecret(ApplicationTokenXpathHelper.getApplicationSecretFromApplicationCredential(xml));
            appToken.setExpires(String.valueOf(System.currentTimeMillis() + 100));
            appToken.setApplicationTokenId(appToken.getMD5hash(appToken.getApplicationID() + appToken.getExpires()));
        }
        return appToken;
    }


}
