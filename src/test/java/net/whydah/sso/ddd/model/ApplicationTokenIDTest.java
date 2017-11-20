package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTokenIDTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationTokenIDTest.class);

    @Test
    public void testIllegalApplicationTokenID() {
        assertFalse(ApplicationTokenID.isValid(""));
        assertFalse(ApplicationTokenID.isValid("234324+2342"));
        assertFalse(ApplicationTokenID.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(ApplicationTokenID.isValid("<html>"));
        assertFalse(ApplicationTokenID.isValid("<javascript:"));
        assertFalse(ApplicationTokenID.isValid("<html>"));
        assertFalse(ApplicationTokenID.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(ApplicationTokenID.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(ApplicationTokenID.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationTokenID.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(ApplicationTokenID.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationTokenID.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(ApplicationTokenID.isValid("+4722334455"));
        assertFalse(ApplicationTokenID.isValid("100"));
        assertFalse(ApplicationTokenID.isValid("243543"));
        assertFalse(ApplicationTokenID.isValid("asadadsaYUYI"));
        assertFalse(ApplicationTokenID.isValid("234324-2RT2"));


    }

    @Test
    public void testOKApplicationTokenID() {
        assertTrue(ApplicationTokenID.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(ApplicationTokenID.isValid(UUID.randomUUID().toString()));
    }


}