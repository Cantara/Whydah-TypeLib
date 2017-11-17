package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoleNameTest {


    private static final Logger log = LoggerFactory.getLogger(RoleNameTest.class);

    @Test
    public void testIllegalRoleName() {
        assertFalse(RoleName.isValid(""));
        assertFalse(RoleName.isValid("234324+2342"));
        assertFalse(RoleName.isValid("<html>"));
        assertFalse(RoleName.isValid("<javascript:"));
        assertFalse(RoleName.isValid("<html>"));
        assertFalse(RoleName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(RoleName.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(RoleName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RoleName.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(RoleName.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RoleName.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(RoleName.isValid("+4722334455"));

    }

    @Test
    public void testOKRoleName() {
        assertTrue(RoleName.isValid("abc"));  // to short
        assertTrue(RoleName.isValid("243543"));
        assertTrue(RoleName.isValid("asadadsaYUYI"));
        assertTrue(RoleName.isValid("ola.nordman"));
        assertTrue(RoleName.isValid("ola.nordman@test.no"));
        assertTrue(RoleName.isValid("22334455"));
        assertTrue(RoleName.isValid("TestRolename111f653f-7958-474e-b5ff-9ef428323e27"));
        assertTrue(RoleName.isValid(UUID.randomUUID().toString()));
    }


}