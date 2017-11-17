package net.whydah.sso.ddd.model;

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
        assertFalse(RoleId.isValid("httpa://whydahdev.cantara.no"));
        assertFalse(RoleId.isValid("-1"));
        assertFalse(RoleId.isValid("1"));
        assertFalse(RoleId.isValid("\\"));
        assertFalse(RoleId.isValid(" "));
        assertFalse(RoleId.isValid("    "));
    }

    @Test
    public void testOKRoleId() {
        assertTrue(RoleId.isValid("useradmin"));
        assertTrue(RoleId.isValid("whydahuseradmin"));
        assertTrue(RoleId.isValid("petter_smart "));
        assertTrue(RoleId.isValid(UUID.randomUUID().toString()));
        assertTrue(RoleId.isValid("petter.smart "));
        assertFalse(RoleId.isValid("143"));
        assertFalse(RoleId.isValid("abc"));

    }

}