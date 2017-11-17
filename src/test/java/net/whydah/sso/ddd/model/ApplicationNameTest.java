package net.whydah.sso.ddd.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationNameTest {

    @Test
    public void testIllegalWhydahNames() {
        assertFalse(ApplicationName.isValid("<html>"));
    }

    @Test
    public void testOKIdentities() {
        assertTrue(ApplicationName.isValid(""));
        assertTrue(ApplicationName.isValid("243543"));
        assertTrue(ApplicationName.isValid("asadadsaYUYI"));
        assertTrue(ApplicationName.isValid("234324-2RT2"));
        assertTrue(ApplicationName.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(ApplicationName.isValid(UUID.randomUUID().toString()));
        assertTrue(ApplicationName.isValid("23"));
        assertTrue(ApplicationName.isValid("234324+2342"));
        assertTrue(ApplicationName.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
    }


}