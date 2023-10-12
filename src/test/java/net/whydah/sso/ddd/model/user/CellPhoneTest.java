package net.whydah.sso.ddd.model.user;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CellPhoneTest {

    private static final Logger log = LoggerFactory.getLogger(CellPhoneTest.class);

    @Test
    public void testIllegalCellPhone() {
        assertFalse(CellPhone.isValid("httpa://whydahdev.cantara.no"));  // strange characters
        assertFalse(CellPhone.isValid("-1"));  // short
        assertFalse(CellPhone.isValid("143"));  // short
        assertFalse(CellPhone.isValid("000000000"));  // short
        assertFalse(CellPhone.isValid("abc"));  // short
        assertFalse(CellPhone.isValid(UUID.randomUUID().toString()));
    }

    @Test
    public void testOKCellPhone() {
        assertTrue(CellPhone.isValid("96909999"));
        assertTrue(CellPhone.isValid("96909999"));
        assertTrue(CellPhone.isValid("969 09 999"));
        assertTrue(CellPhone.isValid("9690 9999"));
        assertTrue(CellPhone.isValid("+47 40050000"));
        assertTrue(CellPhone.isValid("50070022"));
        assertTrue(CellPhone.isValid("+47 919 05054"));
        assertTrue(CellPhone.isValid("(+47) 919 05054"));
        


        
    }


}