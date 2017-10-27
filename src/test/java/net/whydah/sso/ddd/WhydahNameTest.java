package net.whydah.sso.ddd;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhydahNameTest {

    @Test
    public void testIllegalWhydahNames() {
        assertFalse(new WhydahName("<html>").isValid());
    }

    @Test
    public void testOKIdentities() {
        assertTrue(new WhydahName("").isValid());
        assertTrue(new WhydahName("243543").isValid());
        assertTrue(new WhydahName("asadadsaYUYI").isValid());
        assertTrue(new WhydahName("234324-2RT2").isValid());
        assertTrue(new WhydahName("2342424-2342-2342342-342-2342342-24").isValid());
        assertTrue(new WhydahName(UUID.randomUUID().toString()).isValid());
        assertTrue(new WhydahName("23").isValid());
        assertTrue(new WhydahName("234324+2342").isValid());
        assertTrue(new WhydahName("2342424-2342342-2342342-2342342-2342342-23424323-2342423").isValid());
    }


}