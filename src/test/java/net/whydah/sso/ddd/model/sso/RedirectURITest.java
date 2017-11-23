package net.whydah.sso.ddd.model.sso;

import net.whydah.sso.ddd.model.application.RedirectURI;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RedirectURITest {

    private static final Logger log = LoggerFactory.getLogger(RedirectURITest.class);

    @Test
    public void testIllegalRedirectURI() {
        assertFalse(RedirectURI.isValid("<javascript:"));
        assertFalse(RedirectURI.isValid("<html>"));
        
        assertFalse(RedirectURI.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(RedirectURI.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(RedirectURI.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RedirectURI.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(RedirectURI.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(RedirectURI.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
    }

    @Test
    public void testOKRedirectURI() {
    	assertTrue(RedirectURI.isValid(""));
        assertTrue(RedirectURI.isValid("243543"));
        assertTrue(RedirectURI.isValid("welcome"));
        assertTrue(RedirectURI.isValid("234324-2RT2"));
        assertTrue(RedirectURI.isValid("https://whydahdev.cantara.no/useradmin?ticket=2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertTrue(RedirectURI.isValid(UUID.randomUUID().toString()));
        assertTrue(RedirectURI.isValid("login"));
        //this is invalid, no plus (or white space)
        assertTrue(RedirectURI.isValid("234324+2342"));
        assertTrue(RedirectURI.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
    }


}