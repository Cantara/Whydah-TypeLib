package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationSecretTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationSecretTest.class);

    @Test
    public void testIllegalApplicationSecret() {
        assertFalse(ApplicationSecret.isValid("httpa://whydahdev.cantara.no"));  // strange characters
        assertFalse(ApplicationSecret.isValid("-1"));  // short
        assertFalse(ApplicationSecret.isValid("143"));  // short
        assertFalse(ApplicationSecret.isValid("abc"));  // short
    }

    @Test
    public void testOKApplicationSecret() {
        //assertTrue(ApplicationSecret.isValid("useradmin"));
        //assertTrue(ApplicationSecret.isValid("11173648731648525"));
        assertTrue(ApplicationSecret.isValid("jeg gikk en tur i skogen hørte og  noe"));
        assertTrue(ApplicationSecret.isValid("petter_smart "));
        assertTrue(ApplicationSecret.isValid(UUID.randomUUID().toString()));

    }


}