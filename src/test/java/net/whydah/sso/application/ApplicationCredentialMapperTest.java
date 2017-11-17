package net.whydah.sso.application;

import net.whydah.sso.application.mappers.ApplicationCredentialMapper;
import net.whydah.sso.application.types.ApplicationCredential;
import org.junit.Test;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import static net.whydah.sso.application.helpers.ApplicationCredentialHelper.getDummyApplicationCredential;

public class ApplicationCredentialMapperTest {


    @Test
    public void testBadXMLInjection() throws Exception {
        String badPayload = "%3c!DOCTYPE%20foo%20[%3c!ENTITY%20xxe5zzwm%20SYSTEM%20%22file%3a%2f%2f%2fetc%2fpasswd%22%3e%20]%3e%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3esikkerhetstest1%26xxe5zzwm%3b%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eUt%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3ehttps%3a%2f%2finn-qa-ut.opplysningen.no%2ftokenservice%2f%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e%0a";
        String newBadPayload = "%3c!DOCTYPE%20foo%20[%3c!ENTITY%20xxe5zzwm%20SYSTEM%20%22file%3a%2f%2f%2fetc%2fpasswd%22%3e%20]%3e%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3esikkerhetstest1%26xxe5zzwm%3b%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eUt%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3ehttps%3a%2f%2finn-qa-ut.opplysningen.no%2ftokenservice%2f%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e%0a";
        XPath xPath = XPathFactory.newInstance().newXPath();

        System.out.println("Length:{}" + badPayload.indexOf("applicationcredential"));

        if (badPayload.indexOf("applicationcredential") < 70) {
            String applicationId = (String) xPath.evaluate("//applicationID", badPayload, XPathConstants.STRING);
            ApplicationCredential ac = ApplicationCredentialMapper.fromXml(badPayload);
            System.out.println(ac);
        }

    }

    @Test
    public void testApplicationSecretMarshalling() throws Exception {
        ApplicationCredential ac = new ApplicationCredential("id", "name", "secretsecret");
        String xmlCredential = ApplicationCredentialMapper.toXML(ac);
        System.out.println(xmlCredential);
        System.out.println("Length:{}" + xmlCredential.indexOf("applicationcredential"));
    }

    @Test
    public void testApplicationTokenHelper() {
        ApplicationCredential t = ApplicationCredentialMapper.fromXml(getDummyApplicationCredential());

    }

}
