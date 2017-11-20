package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationIdTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationIdTest.class);

    @Test
    public void testIllegalApplicationId() {
        assertFalse(ApplicationId.isValid(""));
        assertFalse(ApplicationId.isValid("234324+2342"));
        assertFalse(ApplicationId.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(ApplicationId.isValid("<html>"));
        assertFalse(ApplicationId.isValid("<javascript:"));
        assertFalse(ApplicationId.isValid("<html>"));
        assertFalse(ApplicationId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(ApplicationId.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(ApplicationId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationId.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(ApplicationId.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationId.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(ApplicationId.isValid("+4722334455"));


    }

    @Test
    public void testOKApplicationId() {
        assertTrue(ApplicationId.isValid("100"));
        assertTrue(ApplicationId.isValid("243543"));
        assertTrue(ApplicationId.isValid("asadadsaYUYI"));
        assertTrue(ApplicationId.isValid("234324-2RT2"));
        assertTrue(ApplicationId.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(ApplicationId.isValid(UUID.randomUUID().toString()));
    }


}