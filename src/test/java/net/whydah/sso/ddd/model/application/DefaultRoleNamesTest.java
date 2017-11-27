package net.whydah.sso.ddd.model.application;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefaultRoleNamesTest {

    private static final Logger log = LoggerFactory.getLogger(DefaultRoleNamesTest.class);

    @Test
    public void testIllegalDefaultRoleNames() {
        assertFalse(DefaultRoleNames.isValid("234324+2342"));
        assertFalse(DefaultRoleNames.isValid("<html>"));
        assertFalse(DefaultRoleNames.isValid("<javascript:"));
        assertFalse(DefaultRoleNames.isValid("<html>"));
        assertFalse(DefaultRoleNames.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(DefaultRoleNames.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(DefaultRoleNames.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(DefaultRoleNames.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(DefaultRoleNames.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(DefaultRoleNames.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(DefaultRoleNames.isValid("+4722334455"));

    }

    @Test
    public void testOKDefaultRoleNames() {
        assertTrue(DefaultRoleNames.isValid(""));
        assertTrue(DefaultRoleNames.isValid("abc"));  // to short
        assertTrue(DefaultRoleNames.isValid("243543"));
        assertTrue(DefaultRoleNames.isValid("asadadsaYUYI"));
        assertTrue(DefaultRoleNames.isValid("ola.nordman"));
        assertTrue(DefaultRoleNames.isValid("ola.nordman@test.no"));
        assertTrue(DefaultRoleNames.isValid("22334455"));
        assertTrue(DefaultRoleNames.isValid("TestRolename111f653f-7958-474e-b5ff-9ef428323e27"));
        assertTrue(DefaultRoleNames.isValid(UUID.randomUUID().toString()));
        assertTrue(DefaultRoleNames.isValid("Employee"));
        assertTrue(DefaultRoleNames.isValid("user, admin"));
    }


}