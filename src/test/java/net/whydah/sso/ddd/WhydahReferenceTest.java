package net.whydah.sso.ddd;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhydahReferenceTest {

    @Test
    public void testIllegalReferences() {
        assertFalse(new WhydahReference("").isValid());
        assertFalse(new WhydahReference("23").isValid());
        assertFalse(new WhydahReference("234324+2342").isValid());
        assertFalse(new WhydahReference("2342424-2342342-2342342-2342342-2342342-23424323-2342423").isValid());
        assertFalse(new WhydahReference("<html>").isValid());
    }

    @Test
    public void testOKReferences() {
        assertTrue(new WhydahReference("243543").isValid());
        assertTrue(new WhydahReference("asadadsaYUYI").isValid());
        assertTrue(new WhydahReference("234324-2RT2").isValid());
        assertTrue(new WhydahReference("2342424-2342-2342342-342-2342342-24").isValid());
        assertTrue(new WhydahReference(UUID.randomUUID().toString()).isValid());
    }
}