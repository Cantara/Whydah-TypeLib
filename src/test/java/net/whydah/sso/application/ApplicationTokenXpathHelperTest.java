package net.whydah.sso.application;

import net.whydah.sso.application.helpers.ApplicationTokenXpathHelper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class ApplicationTokenXpathHelperTest {

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
        System.out.printf("Apptokenid: " + applicationTokenID);
        assertTrue(applicationTokenID != null && applicationTokenID.length() > 10);
    }
}
