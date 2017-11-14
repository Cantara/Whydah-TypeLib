package net.whydah.sso.ddd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import net.whydah.sso.ddd.model.ApplicationId;

import org.junit.Test;

public class WhydahIdentityTest {

    @Test
    public void testIllegalIdentities() {
        assertFalse(ApplicationId.isValid(""));
        assertFalse(ApplicationId.isValid("234324+2342"));
        assertFalse(ApplicationId.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(ApplicationId.isValid("<html>"));
       
    }

    @Test
    public void testOKIdentities() {
        assertTrue(ApplicationId.isValid("100"));
        assertTrue(ApplicationId.isValid("243543"));
        assertTrue(ApplicationId.isValid("asadadsaYUYI"));
        assertTrue(ApplicationId.isValid("234324-2RT2"));
        assertTrue(ApplicationId.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(ApplicationId.isValid(UUID.randomUUID().toString()));
    }


}