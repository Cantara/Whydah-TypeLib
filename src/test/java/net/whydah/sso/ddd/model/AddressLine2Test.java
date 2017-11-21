package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddressLine2Test

{
    private static final Logger log = LoggerFactory.getLogger(AddressLine2Test.class);

    @Test
    public void testIllegalAddressLine2() {
        assertFalse(AddressLine2.isValid("234324+2342"));
        assertFalse(AddressLine2.isValid("<html>"));
        assertFalse(AddressLine2.isValid("<javascript:"));
        assertFalse(AddressLine2.isValid("<html>"));
        assertFalse(AddressLine2.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(AddressLine2.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(AddressLine2.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(AddressLine2.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(AddressLine2.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(AddressLine2.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(AddressLine2.isValid("+4722334455"));

    }

    @Test
    public void testOKAddressLine2() {
        assertTrue(AddressLine2.isValid(""));
        assertTrue(AddressLine2.isValid("abc"));  // to short
        assertTrue(AddressLine2.isValid("0750 Oslo"));
        assertTrue(AddressLine2.isValid("0750 Oslo, Norway"));
        assertTrue(AddressLine2.isValid("Møllefaret"));
        assertTrue(AddressLine2.isValid("Møllefaret 30E"));
        assertTrue(AddressLine2.isValid(UUID.randomUUID().toString()));
    }


}