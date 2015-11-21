package net.whydah.sso.application.helpers;

public class ApplicationCredentialHelper {


    public static String getDummyApplicationCredential() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n " +
                "<applicationcredential>\n" +
                "    <params>\n" +
                "        <applicationID>XXX</applicationID>\n" +
                "        <applicationName>DummyApplication</applicationName>\n" +
                "        <applicationSecret>a very long and super secret passphrase</applicationSecret>\n" +
                "    </params> \n" +
                "</applicationcredential>\n";
    }


}
