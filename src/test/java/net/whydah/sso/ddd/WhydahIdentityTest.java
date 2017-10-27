package net.whydah.sso.ddd;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhydahIdentityTest {

    @Test
    public void testIllegalIdentities() {
        assertFalse(new WhydahIdentity("").isValid());
        assertFalse(new WhydahIdentity("23").isValid());
        assertFalse(new WhydahIdentity("234324+2342").isValid());
        assertFalse(new WhydahIdentity("2342424-2342342-2342342-2342342-2342342-23424323-2342423").isValid());
        assertFalse(new WhydahIdentity("<html>").isValid());
    }

    @Test
    public void testOKIdentities() {
        assertTrue(new WhydahIdentity("243543").isValid());
        assertTrue(new WhydahIdentity("asadadsaYUYI").isValid());
        assertTrue(new WhydahIdentity("234324-2RT2").isValid());
        assertTrue(new WhydahIdentity("2342424-2342-2342342-342-2342342-24").isValid());
        assertTrue(new WhydahIdentity(UUID.randomUUID().toString()).isValid());
    }


}