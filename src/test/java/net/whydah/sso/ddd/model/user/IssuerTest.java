package net.whydah.sso.ddd.model.user;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IssuerTest {
    private static final Logger log = LoggerFactory.getLogger(IssuerTest.class);

    @Test
    public void testIllegalIssuer() {

        assertFalse(Issuer.isValid("<html>"));
        assertFalse(Issuer.isValid("<javascript:"));
        assertFalse(Issuer.isValid("<html>"));
        assertFalse(Issuer.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(Issuer.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(Issuer.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Issuer.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(Issuer.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(Issuer.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));

    }

    @Test
    public void testOKIssuer() {
        assertTrue(Issuer.isValid("https://wiki.cantara.no/download/attachments/38962704/whydah"));

    }

}