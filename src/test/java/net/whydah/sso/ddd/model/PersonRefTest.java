package net.whydah.sso.ddd.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonRefTest {

    @Test
    public void testIllegalPersonRef() {


        assertFalse(PersonRef.isValid("234324+2342"));
        assertFalse(PersonRef.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(PersonRef.isValid("<html>"));
    }

    @Test
    public void testOKPersonRef() {
        assertTrue(PersonRef.isValid("243543"));
        assertTrue(PersonRef.isValid("asadadsaYUYI"));
        assertTrue(PersonRef.isValid("234324-2RT2"));
        assertTrue(PersonRef.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(PersonRef.isValid(UUID.randomUUID().toString()));
    }
}