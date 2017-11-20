package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UIDTest {

    private static final Logger log = LoggerFactory.getLogger(UIDTest.class);

    @Test
    public void testIllegalUID() {
        assertFalse(UID.isValid("httpa://whydahdev.cantara.no"));
        assertFalse(UID.isValid("-1"));  // Negative delta does not give a meaning
        assertFalse(UID.isValid("143"));  // Too high interval
        assertFalse(UID.isValid("abc"));  // Too far in the future
        assertFalse(UID.isValid("<html>"));
        assertFalse(UID.isValid("<javascript:"));
        assertFalse(UID.isValid("<html>"));
        assertFalse(UID.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(UID.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(UID.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(UID.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(UID.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(UID.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(UID.isValid("+4722334455"));

    }

    @Test
    public void testOKUID() {
        assertTrue(UID.isValid("useradmin"));
        assertTrue(UID.isValid("whydahuseradmin"));
        assertTrue(UID.isValid("petter_smart "));
        assertTrue(UID.isValid("petter.smart "));
        assertTrue(UID.isValid(UUID.randomUUID().toString()));

    }

}