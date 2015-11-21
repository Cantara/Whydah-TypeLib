package net.whydah.sso.application.mappers;


import net.whydah.sso.application.helpers.ApplicationTokenXpathHelper;
import net.whydah.sso.application.types.ApplicationToken;

public class ApplicationTokenMapper {

    public static String toXML(ApplicationToken applicationToken) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
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


    public static ApplicationToken fromApplicationCredentialXML(String xml) {
        ApplicationToken appToken = new ApplicationToken();
        appToken.setApplicationID(ApplicationTokenXpathHelper.getApplicationIDFromApplicationCredential(xml));
        appToken.setApplicationName(ApplicationTokenXpathHelper.getApplicationNameFromApplicationCredential(xml));
        appToken.setApplicationSecret(ApplicationTokenXpathHelper.getApplicationSecretFromApplicationCredential(xml));
        appToken.setExpires(String.valueOf(System.currentTimeMillis() + 100));
        appToken.setApplicationTokenId(appToken.getMD5hash(appToken.getApplicationID() + appToken.getExpires()));
        return appToken;
    }


}
