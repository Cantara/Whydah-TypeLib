package net.whydah.sso.ddd.model.user;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenIdTest {
    private static final Logger log = LoggerFactory.getLogger(UserTokenIdTest.class);

    @Test
    public void testIllegalUserTokenId() {
        assertFalse(UserTokenId.isValid(""));
        assertFalse(UserTokenId.isValid("234324+2342"));
        assertFalse(UserTokenId.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(UserTokenId.isValid("<html>"));
        assertFalse(UserTokenId.isValid("<javascript:"));
        assertFalse(UserTokenId.isValid("<html>"));
        assertFalse(UserTokenId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'&hashContent='%2bprompt('XXS-PoC2')%2b'"));
        assertFalse(UserTokenId.isValid("welcome'%2balert('XXS-PoC1')%2b'&hashContent='%2balert('XXS-PoC2')%2b'"));
        assertFalse(UserTokenId.isValid("alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(UserTokenId.isValid("welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(UserTokenId.isValid("https://whydahdev.cantara.no/sso/action?alert'%2bconfirm('XXS-PoC1')%2b'"));
        assertFalse(UserTokenId.isValid("https://whydahdev.cantara.no/sso/action?welcome'%2balert('XXS-PoC1')%2b'"));
        assertFalse(UserTokenId.isValid("+4722334455"));
        assertFalse(UserTokenId.isValid("100"));
        assertFalse(UserTokenId.isValid("243543"));
        assertFalse(UserTokenId.isValid("asadadsaYUYI"));
        assertFalse(UserTokenId.isValid("234324-2RT2"));


    }

    @Test
    public void testOKUserTokenId() {
        assertTrue(UserTokenId.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(UserTokenId.isValid(UUID.randomUUID().toString()));
    }


}