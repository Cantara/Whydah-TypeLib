package net.whydah.sso.ddd.model.userrole;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoleIdTest {


    private static final Logger log = LoggerFactory.getLogger(RoleIdTest.class);

    @Test
    public void testIllegalRoleId() {
        assertFalse(RoleId.isValid("<html>"));
        assertFalse(RoleId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(RoleId.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(RoleId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RoleId.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(RoleId.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RoleId.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(RoleId.isValid("httpa://whydahdev.cantara.no"));
        assertFalse(RoleId.isValid("\\"));
    }

    @Test
    public void testOKRoleId() {
        assertTrue(RoleId.isValid("useradmin"));
        assertTrue(RoleId.isValid("whydahuseradmin"));
        assertTrue(RoleId.isValid("petter_smart "));
        assertTrue(RoleId.isValid(UUID.randomUUID().toString()));
        assertTrue(RoleId.isValid("143"));
        assertTrue(RoleId.isValid("abc"));
        assertTrue(RoleId.isValid("1"));
        assertTrue(RoleId.isValid(" "));
        assertTrue(RoleId.isValid("    "));

    }

}