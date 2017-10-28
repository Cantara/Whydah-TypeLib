package net.whydah.sso.application.types;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ApplicationCredentialTest {


    @Test
    public void testIllegalApplicationCredentials() {
    }

    @Test
    public void testOKIdentities() {
        ApplicationCredential cred = new ApplicationCredential("193", "myapp", "dummy");
        assertTrue("193".equalsIgnoreCase(cred.getApplicationID()));
    }

}