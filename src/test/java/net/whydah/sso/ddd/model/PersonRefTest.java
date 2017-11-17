package net.whydah.sso.ddd.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonRefTest {

    @Test
    public void testIllegalPersonRef() {

        assertFalse(PersonRef.isValid("<html>"));
        assertFalse(PersonRef.isValid("<javascript:"));
        assertFalse(PersonRef.isValid("<html>"));
        assertFalse(PersonRef.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(PersonRef.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(PersonRef.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(PersonRef.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(PersonRef.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(PersonRef.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));

    }

    @Test
    public void testOKPersonRef() {
        assertTrue(PersonRef.isValid("243543"));
        assertTrue(PersonRef.isValid("asadadsaYUYI"));
        assertTrue(PersonRef.isValid("234324+2342"));
        assertTrue(PersonRef.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertTrue(PersonRef.isValid("234324-2RT2"));
        assertTrue(PersonRef.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(PersonRef.isValid(UUID.randomUUID().toString()));
//        assertTrue(PersonRef.isValid("https://whydahdev.cantara.no/lookup?id=236746'"));
//        assertTrue(PersonRef.isValid("https://whydahdev.cantara.no/lookup/?id=236746'"));
    }
}