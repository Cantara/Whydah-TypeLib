package net.whydah.sso.user.types;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenIDTest {

    @Test
    public void testIllegalUserTokenIDs() {
        assertFalse(new UserTokenID("").isValid());
        assertFalse(new UserTokenID("23").isValid());
        assertFalse(new UserTokenID("234324+2342").isValid());
        assertFalse(new UserTokenID("2342424-2342342-2342342-2342342-2342342-23424323-2342423").isValid());
        assertFalse(new UserTokenID("<html>").isValid());
    }

    @Test
    public void testOKUserTokenIDs() {
        assertTrue(new UserTokenID("243543").isValid());
        assertTrue(new UserTokenID("asadadsaYUYI").isValid());
        assertTrue(new UserTokenID("234324-2RT2").isValid());
        assertTrue(new UserTokenID("2342424-2342-2342342-342-2342342-24").isValid());
        assertTrue(new UserTokenID(UUID.randomUUID().toString()).isValid());
    }
}