package net.whydah.sso.ddd.model.customer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LastNameTest {

    private static final Logger log = LoggerFactory.getLogger(LastNameTest.class);

    @Test
    public void testIllegalLastName() {
        assertFalse(LastName.isValid(""));
        assertFalse(LastName.isValid("234324+2342"));
        assertFalse(LastName.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(LastName.isValid("<html>"));
        assertFalse(LastName.isValid("<javascript:"));
        assertFalse(LastName.isValid("<html>"));
        assertFalse(LastName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(LastName.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(LastName.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(LastName.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(LastName.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(LastName.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(LastName.isValid("22334455"));
        assertFalse(LastName.isValid("+4722334455"));
        assertFalse(LastName.isValid("243543"));
        assertFalse(LastName.isValid("2342424-2342-2342342-342-2342342-24"));
        assertFalse(LastName.isValid(UUID.randomUUID().toString()));

    }

    @Test
    public void testOKLastName() {
        assertTrue(LastName.isValid("asadadsaYUYI"));
        assertTrue(LastName.isValid("Nordman Olsen"));
        assertTrue(LastName.isValid("Nordman"));
        assertTrue(LastName.isValid("Pierré"));
        assertTrue(LastName.isValid("Pierrä"));

        assertTrue(LastName.isValid("Márquez"));
        assertTrue(LastName.isValid("abc"));
        assertTrue(LastName.isValid("Lie"));
        assertTrue(LastName.isValid("Lindström"));
        assertTrue(LastName.isValid("Bø"));
        assertTrue(LastName.isValid("Ødegård"));
        assertTrue(LastName.isValid("Pinilla-Millán"));
        assertTrue(LastName.isValid("Ivar Vándar"));
        assertTrue(LastName.isValid("Alexander Fahlström"));
    }


}