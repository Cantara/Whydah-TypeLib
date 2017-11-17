package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationNameTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationNameTest.class);

    @Test
    public void testIllegalApplicationName() {

        assertFalse(ApplicationName.isValid("<html>"));
        assertFalse(ApplicationName.isValid("<javascript:"));
        assertFalse(ApplicationName.isValid("<html>"));
        assertFalse(ApplicationName.isValid("abc"));  // to short
        assertFalse(ApplicationName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(ApplicationName.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(ApplicationName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationName.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(ApplicationName.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationName.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(ApplicationName.isValid(""));
        assertFalse(ApplicationName.isValid("23"));

    }

    @Test
    public void testOKApplicationName() {
        assertTrue(ApplicationName.isValid("243543"));
        assertTrue(ApplicationName.isValid("asadadsaYUYI"));
        assertTrue(ApplicationName.isValid("My application"));
        assertTrue(ApplicationName.isValid("Finn"));
        assertTrue(ApplicationName.isValid(UUID.randomUUID().toString()));
        assertTrue(ApplicationName.isValid("234324+2342"));
        assertTrue(ApplicationName.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
    }


}