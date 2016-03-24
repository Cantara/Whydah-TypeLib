package net.whydah.sso.application.helpers;

public class ApplicationCredentialHelper {


    public static String getDummyApplicationCredential() {
        return
                "<applicationcredential>\n" +
                        "    <params>\n" +
                        "        <applicationID>XXX</applicationID>\n" +
                        "        <applicationName>DummyApplication</applicationName>\n" +
                        "        <applicationSecret>a very long and super secret passphrase</applicationSecret>\n" +
                        "        <applicationurl> http(s)://myUrl </applicationurl>\n" +
                        "        <minimumsecuritylevel>2</minimumsecuritylevel>\n" +
                        "     </params> \n" +
                        "</applicationcredential>\n";
    }


}
