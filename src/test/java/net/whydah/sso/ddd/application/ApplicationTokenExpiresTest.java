package net.whydah.sso.ddd.application;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTokenExpiresTest {

    @Test
    public void testIllegalApplicationTokenExpires() {
        assertFalse(new ApplicationTokenExpires(-1).isValid());
        assertFalse(new ApplicationTokenExpires(432472186).isValid());
    }

    @Test
    public void testOKApplicationTokenExpires() {
        assertTrue(new ApplicationTokenExpires(100).isValid());
        assertTrue(new ApplicationTokenExpires(243543).isValid());
        assertTrue(new ApplicationTokenExpires(23226566).isValid());
    }

}