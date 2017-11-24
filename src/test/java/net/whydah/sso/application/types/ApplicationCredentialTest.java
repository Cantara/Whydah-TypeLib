package net.whydah.sso.application.types;

import net.whydah.sso.application.mappers.ApplicationCredentialMapper;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationCredentialTest {


    @Test
    public void testOKApplicationCredentials() {
        ApplicationCredential cred = new ApplicationCredential("193", "myapplication", "dummysecret");
        assertTrue("193".equalsIgnoreCase(cred.getApplicationID()));
    }

    @Test
    public void testIllegalApplicationCredentials() {
        try {
            String XXEexample = "%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3eXXX1asdtp%26lt%3ba%20xmlns%3aa%3d%26apos%3bhttp%3a%2f%2fwww.w3.org%2f1999%2fxhtml%26apos%3b%26gt%3b%26lt%3ba%3abody%20onload%3d%26apos%3bdocument.location%3d1%26apos%3b%2f%26gt%3b%26lt%3b%2fa%26gt%3ba6c37%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eDummyApplication%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3e%20https%3a%2f%2fxx.yy.zz.no%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e";
            ApplicationCredential cred = ApplicationCredentialMapper.fromXml(XXEexample);
            assertTrue(cred.getApplicationID() == null);
        } catch (Exception e) {
            // Exceptions might be ok
        }
        try {
            String XXE2example = "%3c!DOCTYPE%20foo%20[%3c!ENTITY%20xxe5zzwm%20SYSTEM%20%22file%3a%2f%2f%2fetc%2fpasswd%22%3e%20]%3e%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3esikkerhetstest1%26xxe5zzwm%3b%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eUt%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3ehttps%3a%2f%2finn-qa-ut.opplysningen.no%2ftokenservice%2f%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e%0a";
            ApplicationCredential cred = ApplicationCredentialMapper.fromXml(XXE2example);
            assertTrue(cred.getApplicationID() == null);
        } catch (Exception e) {
            // Exceptions might be ok
        }

        try {
            String XXE3example = "%3c!DOCTYPE%20foo%20[%3c!ENTITY%20xxe5zzwm%20SYSTEM%20%22file%3a%2f%2f%2fetc%2fpasswd%22%3e%20]%3e%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3esikkerhetstest1%26xxe5zzwm%3b%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eUt%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3ehttps%3a%2f%2finn-qa-ut.opplysningen.no%2ftokenservice%2f%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e%0a";
            ApplicationCredential cred = ApplicationCredentialMapper.fromXml(XXE3example);
            assertTrue(cred.getApplicationID() == null);
        } catch (Exception e) {
            // Exceptions might be ok
        }

    }

    @Test
    public void testOldApplicationCredential() {
        String appCredential = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><applicationcredential><appid>app123</appid><appsecret>12312324234</appsecret></applicationcredential>";
        ApplicationCredential applicationCredential = ApplicationCredentialMapper.fromXml(appCredential);
        assertTrue("app123".equalsIgnoreCase(applicationCredential.getApplicationID()));
    }

    @Test
    public void testIllegalApplicationCredentia() {
        assertFalse(ApplicationCredential.isValid(""));
        assertFalse(ApplicationCredential.isValid("234324+2342"));
        assertFalse(ApplicationCredential.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(ApplicationCredential.isValid("<html>"));
        assertFalse(ApplicationCredential.isValid("%3c!DOCTYPE%20foo%20[%3c!ENTITY%20xxe5zzwm%20SYSTEM%20%22file%3a%2f%2f%2fetc%2fpasswd%22%3e%20]%3e%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3esikkerhetstest1%26xxe5zzwm%3b%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eUt%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3ehttps%3a%2f%2finn-qa-ut.opplysningen.no%2ftokenservice%2f%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e%0a"));
        assertFalse(ApplicationCredential.isValid("%3c!DOCTYPE%20foo%20[%3c!ENTITY%20xxe5zzwm%20SYSTEM%20%22file%3a%2f%2f%2fetc%2fpasswd%22%3e%20]%3e%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3esikkerhetstest1%26xxe5zzwm%3b%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eUt%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3ehttps%3a%2f%2finn-qa-ut.opplysningen.no%2ftokenservice%2f%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e%0a"));
        assertFalse(ApplicationCredential.isValid("%3capplicationcredential%3e%0a%20%20%20%20%3cparams%3e%0a%20%20%20%20%20%20%20%20%3capplicationID%3eXXX1asdtp%26lt%3ba%20xmlns%3aa%3d%26apos%3bhttp%3a%2f%2fwww.w3.org%2f1999%2fxhtml%26apos%3b%26gt%3b%26lt%3ba%3abody%20onload%3d%26apos%3bdocument.location%3d1%26apos%3b%2f%26gt%3b%26lt%3b%2fa%26gt%3ba6c37%3c%2fapplicationID%3e%0a%20%20%20%20%20%20%20%20%3capplicationName%3eDummyApplication%3c%2fapplicationName%3e%0a%20%20%20%20%20%20%20%20%3capplicationSecret%3eaverylongandsupersecretpassphrase%3c%2fapplicationSecret%3e%0a%20%20%20%20%20%20%20%20%3capplicationurl%3e%20https%3a%2f%2fxx.yy.zz.no%3c%2fapplicationurl%3e%0a%20%20%20%20%20%20%20%20%3cminimumsecuritylevel%3e2%3c%2fminimumsecuritylevel%3e%0a%20%20%20%20%20%3c%2fparams%3e%20%0a%3c%2fapplicationcredential%3e"));
        assertFalse(ApplicationCredential.isValid("<html>"));
        assertFalse(ApplicationCredential.isValid("243543"));
        assertFalse(ApplicationCredential.isValid("asadadsaYUYI"));
        assertFalse(ApplicationCredential.isValid("234324-2RT2"));
        assertFalse(ApplicationCredential.isValid("2342424-2342-2342342-342-2342342-24"));
        assertFalse(ApplicationCredential.isValid(UUID.randomUUID().toString()));


    }

    @Test
    public void testOKApplicationCredentia() {
        assertTrue(ApplicationCredential.isValid("<?xml version='1.0' encoding='UTF-8' standalone='yes'?><applicationcredential><appid>app123</appid><appsecret>12312324234</appsecret></applicationcredential>"));
        ApplicationCredential ac = new ApplicationCredential("mytestid", "mytestname", "secretsecret");
        String xmlCredential = ApplicationCredentialMapper.toXML(ac);
        assertTrue(ApplicationCredential.isValid(xmlCredential));
        assertTrue(ApplicationCredential.isValid(ApplicationCredentialMapper.toXML(new ApplicationCredential("id", "name", "secretsecret"))));
    }

}