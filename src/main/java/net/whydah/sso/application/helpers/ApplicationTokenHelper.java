package net.whydah.sso.application.helpers;


public class ApplicationTokenHelper {

    public static String getDummyApplicationToken() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n" +
                "  <applicationtoken>\n" +
                "     <params>\n" +
                "         <applicationtokenID>1cca06386f52f91d9610aa1dbd95b9a9</applicationtokenID>\n" +
                "         <applicationid>2210</applicationid>\n" +
                "         <applicationname>Whydah-UserIdentityBackend</applicationname>\n" +
                "         <expires>1448277365860</expires>\n" +
                "     </params> \n" +
                "     <Url type=\"application/xml\" method=\"POST\"                 template=\"https://whydahdev.cantara.no/tokenservice/user/1cca06386f52f91d9610aa1dbd95b9a9/get_usertoken_by_usertokenid\"/> \n" +
                "   </applicationtoken>";
    }

}
