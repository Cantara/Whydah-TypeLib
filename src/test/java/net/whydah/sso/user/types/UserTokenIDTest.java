package net.whydah.sso.user.types;

import net.whydah.sso.ddd.model.UserTokenId;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenIDTest {

    private static final Logger log = LoggerFactory.getLogger(UserTokenIDTest.class);


    @Test
    public void testIllegalUserTokenIDs() {
        assertFalse(UserTokenId.isValid(""));
        assertFalse(UserTokenId.isValid("23"));
        assertFalse(UserTokenId.isValid("234324+2342"));
        assertFalse(UserTokenId.isValid("2342424-2342342-2342342-2342342-2342342-23424323-2342423"));
        assertFalse(UserTokenId.isValid("<html>"));
        assertFalse(UserTokenId.isValid("243543"));
        assertFalse(UserTokenId.isValid("asadadsaYUYI"));
        assertFalse(UserTokenId.isValid("234324-2RT2"));
    }

    @Test
    public void testOKUserTokenIDs() {
        assertTrue(UserTokenId.isValid("2342424-2342-2342342-342-2342342-24"));
        assertTrue(UserTokenId.isValid(UUID.randomUUID().toString()));
    }
}