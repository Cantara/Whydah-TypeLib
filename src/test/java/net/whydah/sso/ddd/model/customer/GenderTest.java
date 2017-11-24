package net.whydah.sso.ddd.model.customer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenderTest {
    private static final Logger log = LoggerFactory.getLogger(GenderTest.class);

    @Test
    public void testIllegalGender() {
        assertFalse(Gender.isValid("malemalemale"));  // too long
        assertFalse(Gender.isValid("@£½£$@½@$¥"));  // strange characters
        assertFalse(Gender.isValid("@"));  // strange characters
        assertFalse(Gender.isValid("#"));  // strange characters
        assertFalse(Gender.isValid("234324+2342"));
        assertFalse(Gender.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(Gender.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(Gender.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(Gender.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Gender.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(Gender.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Gender.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(Gender.isValid("+4722334455"));
        assertFalse(Gender.isValid("ola.nordman@test.no"));
        assertFalse(Gender.isValid("2342424-2342-2342342-342-2342342-24"));

    }

    @Test
    public void testOKGender() {
        assertTrue(Gender.isValid("M"));
        assertTrue(Gender.isValid("Male"));
        assertTrue(Gender.isValid("female"));
        assertTrue(Gender.isValid("f"));
        assertTrue(Gender.isValid("Åse"));  // should probably block international characters
        assertTrue(Gender.isValid("Bjørnar"));

    }


}