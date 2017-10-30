package net.whydah.sso.ddd.sso;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RedirectURITest {

    @Test
    public void testIllegalRedirectURI() {
        assertFalse(new RedirectURI("<javascript:").isValid());
        assertFalse(new RedirectURI("<html>").isValid());
        assertFalse(new RedirectURI("").isValid());
        assertFalse(new RedirectURI("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'").isValid());
        assertFalse(new RedirectURI("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'").isValid());
        assertFalse(new RedirectURI("alert'%2bconfirm('XXS-PoC1')%2b'").isValid());
        assertFalse(new RedirectURI("welcome'%2balert('XXS-PoC1')%2b'").isValid());



    }

    @Test
    public void testOKRedirectURI() {
        assertTrue(new RedirectURI("243543").isValid());
        assertTrue(new RedirectURI("welcome").isValid());
        assertTrue(new RedirectURI("234324-2RT2").isValid());
        assertTrue(new RedirectURI("https://whydahdev.cantara.no/useradmin?ticket=2342424-2342342-2342342-2342342-2342342-23424323-2342423").isValid());
        assertTrue(new RedirectURI(UUID.randomUUID().toString()).isValid());
        assertTrue(new RedirectURI("login").isValid());
        assertTrue(new RedirectURI("234324+2342").isValid());
        assertTrue(new RedirectURI("2342424-2342342-2342342-2342342-2342342-23424323-2342423").isValid());
    }


}