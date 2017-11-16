package net.whydah.sso.application;

import net.whydah.sso.application.helpers.ApplicationTokenXpathHelper;
import net.whydah.sso.ddd.model.ApplicationId;
import net.whydah.sso.ddd.model.ApplicationSecret;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;


public class ApplicationTokenXpathHelperTest {

    private final static Logger log = LoggerFactory.getLogger(ApplicationTokenXpathHelperTest.class);

    private String appToken = "<applicationtoken>\n" +
            "     <params>\n" +
            "         <applicationtokenID>342ba5c1a790890839fc918bdeffd3bb</applicationtokenID>\n" +
            "         <applicationid>21356253</applicationid>\n" +
            "         <applicationname>ine app</applicationname>\n" +
            "         <expires>1448124696564</expires>\n" +
            "     </params> \n" +
            "     <Url type=\"application/xml\" method=\"POST\"                 template=\"http://localhost:9998/tokenservice/user/342ba5c1a790890839fc918bdeffd3bb/get_usertoken_by_usertokenid\"/> \n" +
            " </applicationtoken>";

    @Test
    public void testGetApplicationTokenIDFromApplicationToken() {

        String applicationTokenID = ApplicationTokenXpathHelper.getApplicationTokenIDFromApplicationToken(appToken);
        log.trace("Apptokenid: " + applicationTokenID);
        assertTrue(applicationTokenID != null && applicationTokenID.length() > 10);
    }


    @Test
    public void testGetApplicationExpiresFromApplicationToken() {

        String expires = ApplicationTokenXpathHelper.getApplicationExpiresFromApplicationToken(appToken);
        log.trace("Expires: " + expires);
        assertTrue(expires != null && expires.length() > 10);
    }

    @Test
    public void testOldApplicationCredential() {
        String appCredential = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><applicationcredential><appid>app123</appid><appsecret>123123</appsecret></applicationcredential>";
        assertTrue(ApplicationId.isValid(ApplicationTokenXpathHelper.getApplicationIDFromApplicationCredential(appCredential)));
        assertTrue(ApplicationSecret.isValid(ApplicationTokenXpathHelper.getApplicationSecretFromApplicationCredential(appCredential)));

    }

}
