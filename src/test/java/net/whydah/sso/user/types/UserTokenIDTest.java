package net.whydah.sso.user.types;

import net.whydah.sso.ddd.model.UserTokenId;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenIDTest {

    @Test
    public void testIllegalUserTokenIDs() {
        assertFalse(UserTokenId.isValid(""));
        assertFalse(UserTokenId.isValid("23"));
        assertFalse(UserTokenId.isValid("234324+2342"));
        assertFalse(UserTokenId.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(UserTokenId.isValid("<html>"));
    }

    @Test
    public void testOKUserTokenIDs() {
        assertTrue(UserTokenId.isValid("243543"));
        assertTrue(UserTokenId.isValid("asadadsaYUYI"));
        assertTrue(UserTokenId.isValid("234324-2RT2"));
        assertTrue(UserTokenId.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(UserTokenId.isValid(UUID.randomUUID().toString()));
    }
}