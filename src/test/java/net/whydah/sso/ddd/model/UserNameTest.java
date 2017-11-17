package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserNameTest {

    private static final Logger log = LoggerFactory.getLogger(UserNameTest.class);

    @Test
    public void testIllegalUserName() {
        assertFalse(UserName.isValid(""));
        assertFalse(UserName.isValid("234324+2342"));
        assertFalse(UserName.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(UserName.isValid("<html>"));
        assertFalse(UserName.isValid("<javascript:"));
        assertFalse(UserName.isValid("<html>"));
        assertFalse(UserName.isValid("abc"));  // to short
        assertFalse(UserName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(UserName.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(UserName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(UserName.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(UserName.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(UserName.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(UserName.isValid("+4722334455"));
        assertFalse(UserName.isValid("2342424-2342-2342342-342-2342342-24"));
        assertFalse(UserName.isValid(UUID.randomUUID().toString()));

    }

    @Test
    public void testOKUserName() {
        assertTrue(UserName.isValid("243543"));
        assertTrue(UserName.isValid("asadadsaYUYI"));
        assertTrue(UserName.isValid("ola.nordman"));
        assertTrue(UserName.isValid("ola.nordman@test.no"));
        assertTrue(UserName.isValid("22334455"));
    }

}