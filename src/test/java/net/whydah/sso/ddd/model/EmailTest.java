package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailTest {

    private static final Logger log = LoggerFactory.getLogger(EmailTest.class);

    @Test
    public void testIllegalEmail() {
        assertFalse(Email.isValid(""));
        assertFalse(Email.isValid("234324+2342"));
        assertFalse(Email.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(Email.isValid("<html>"));
        assertFalse(Email.isValid("<javascript:"));
        assertFalse(Email.isValid("<html>"));
        assertFalse(Email.isValid("abc"));  // to short
        assertFalse(Email.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(Email.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(Email.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Email.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(Email.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Email.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(Email.isValid("22334455"));
        assertFalse(Email.isValid("+4722334455"));
        assertFalse(Email.isValid("asadadsaYUYI"));
        assertFalse(Email.isValid("ola.nordman"));
        assertFalse(Email.isValid("2342424-2342-2342342-342-2342342-24"));
        assertFalse(Email.isValid(UUID.randomUUID().toString()));

    }

    @Test
    public void testOKEmail() {
        assertTrue(Email.isValid("ola.nordman@test.no"));
        assertTrue(Email.isValid("per@[198.234.34.54]"));
        assertTrue(Email.isValid("facebookId1@facebook.com"));
        assertTrue(Email.isValid("facebook_Id1@facebook.com"));
        assertTrue(Email.isValid("_facebookId1@facebook.com"));
        assertTrue(Email.isValid("_facebook_Id_1@facebook.com"));
    }


}