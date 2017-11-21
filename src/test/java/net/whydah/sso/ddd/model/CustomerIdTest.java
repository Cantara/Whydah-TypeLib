package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerIdTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerIdTest.class);

    @Test
    public void testIllegalCustomerId() {
        assertFalse(CustomerId.isValid("234324+2342"));
        assertFalse(CustomerId.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(CustomerId.isValid("<html>"));
        assertFalse(CustomerId.isValid("<javascript:"));
        assertFalse(CustomerId.isValid("<html>"));
        assertFalse(CustomerId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(CustomerId.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(CustomerId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(CustomerId.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(CustomerId.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(CustomerId.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(CustomerId.isValid("+4722334455"));
//        assertFalse(CustomerId.isValid("2342424-2342-2342342-342-2342342-24"));

    }

    @Test
    public void testOKCustomerId() {
        assertTrue(CustomerId.isValid(""));
        assertTrue(CustomerId.isValid("243543"));
        assertTrue(CustomerId.isValid("asadadsaYUYI"));
        assertTrue(CustomerId.isValid("ola.nordman"));
        assertTrue(CustomerId.isValid("ola.nordman@test.no"));
        assertTrue(CustomerId.isValid("22334455"));
        assertTrue(CustomerId.isValid("abc"));
        assertTrue(CustomerId.isValid("sm_username 0"));
        assertTrue(CustomerId.isValid(UUID.randomUUID().toString()));
//        assertTrue(CustomerId.isValid("_temp_username_1434635221960@someDomain.com"));


    }
}
