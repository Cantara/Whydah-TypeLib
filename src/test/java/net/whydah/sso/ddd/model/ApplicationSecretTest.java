package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationSecretTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationSecretTest.class);

    @Test
    public void testIllegalApplicationSecret() {
        assertFalse(ApplicationSecret.isValid("httpa://whydahdev.cantara.no"));  // strange characters
        assertFalse(ApplicationSecret.isValid("-1"));  // short
        assertFalse(ApplicationSecret.isValid("143"));  // short
        assertFalse(ApplicationSecret.isValid("abc"));  // short
        assertFalse(ApplicationSecret.isValid("<html>"));
        assertFalse(ApplicationSecret.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(ApplicationSecret.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(ApplicationSecret.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationSecret.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(ApplicationSecret.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(ApplicationSecret.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));

    }

    @Test
    public void testOKApplicationSecret() {
        assertTrue(ApplicationSecret.isValid("useradminsecret"));
        assertTrue(ApplicationSecret.isValid("11173648731648525"));
        assertTrue(ApplicationSecret.isValid("jeg gikk en tur i skogen hørte og  noe"));
        assertTrue(ApplicationSecret.isValid("petter_smart "));
        assertTrue(ApplicationSecret.isValid(UUID.randomUUID().toString()));
        assertTrue(ApplicationSecret.isValid("*************"));
        assertTrue(ApplicationSecret.isValid("a very long and super secret passphrase"));
        assertTrue(ApplicationSecret.isValid("6r46g3q986Ep6B45B9J46m96D"));




    }


}