package net.whydah.sso.ddd.model.customer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NorwayCellPhoneTest {

    private static final Logger log = LoggerFactory.getLogger(NorwayCellPhoneTest.class);

    @Test
    public void testIllegalNorwayCellPhone() {
        assertFalse(NorwayCellPhone.isValid("httpa://whydahdev.cantara.no"));  // strange characters
        assertFalse(NorwayCellPhone.isValid("-1"));  // short
        assertFalse(NorwayCellPhone.isValid("143"));  // short
        assertFalse(NorwayCellPhone.isValid("22002200"));  // non cellphone
        assertFalse(NorwayCellPhone.isValid("abc"));  // short
        assertFalse(NorwayCellPhone.isValid(UUID.randomUUID().toString()));
        assertFalse(NorwayCellPhone.isValid("+47 919x05054"));  // illegal char
        assertFalse(NorwayCellPhone.isValid("+47 919#05054"));  // illegal
        assertFalse(NorwayCellPhone.isValid("+47 919/05054"));  // illegal
        assertFalse(NorwayCellPhone.isValid("+47 919\\05054"));  // illegal

    }

    @Test
    public void testOKNorwayCellPhone() {
        assertTrue(NorwayCellPhone.isValid("+47 40050000"));
        assertTrue(NorwayCellPhone.isValid("96909999"));
        assertTrue(NorwayCellPhone.isValid("96 90 99 99"));
        assertTrue(NorwayCellPhone.isValid("969 09 999"));
        assertTrue(NorwayCellPhone.isValid("9690 9999"));
        assertTrue(NorwayCellPhone.isValid("50070022"));
        assertTrue(NorwayCellPhone.isValid("+47 919 05054"));
        assertTrue(NorwayCellPhone.isValid("+47 40.050.000"));
        assertTrue(NorwayCellPhone.isValid("+47 400-500-00"));
        assertTrue(NorwayCellPhone.isValid("(+47) 919 05054"));

    }


}