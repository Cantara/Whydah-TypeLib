package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DescriptionTest {


    private static final Logger log = LoggerFactory.getLogger(DescriptionTest.class);

    @Test
    public void testIllegalDescription() {
        assertFalse(Description.isValid("<javascript:"));
        assertFalse(Description.isValid("<html>"));

        assertFalse(Description.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(Description.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(Description.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Description.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(Description.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Description.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
    }

    @Test
    public void testOKDescription() {
        assertTrue(Description.isValid(""));
        assertTrue(Description.isValid("243543"));
        assertTrue(Description.isValid("welcome"));
        assertTrue(Description.isValid("234324-2RT2"));
        assertTrue(Description.isValid(UUID.randomUUID().toString()));
        assertTrue(Description.isValid("login"));
        assertTrue(Description.isValid("234324+2342"));
        assertTrue(Description.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
//        assertTrue(Description.isValid("https://whydahdev.cantara.no/useradmin?ticket=2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertTrue(Description.isValid("Finn den kompetansen du trenger, når du trenger det. Lag eksklusive CV'er tilpasset leseren."));
        assertTrue(Description.isValid("The back-office User Administration module of the Whydah IAM/SSO"));
        assertTrue(Description.isValid("Responsible for configuring which API/useradministration services Whydah IAM/SSO should provide for 3rd parties (outside the innermost firewall)"));




    }

}