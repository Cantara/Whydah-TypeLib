package net.whydah.sso.ddd.application;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTokenExpiresTest {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTokenExpiresTest.class);

    @Test
    public void testIllegalApplicationTokenExpires() throws Exception {
        assertFalse(new ApplicationTokenExpires(-1).isValid());  // Negative delta does not give a meaning
        assertFalse(new ApplicationTokenExpires("-1").isValid());  // Negative delta does not give a meaning
        assertFalse(new ApplicationTokenExpires(432472186).isValid());  // Too high interval
        assertFalse(new ApplicationTokenExpires(String.valueOf((System.currentTimeMillis()) - 300 * 1000)).isValid());  // time in the past
        assertFalse(new ApplicationTokenExpires("432472186").isValid());  // Too high interval
        assertFalse(new ApplicationTokenExpires(140943309377L).isValid());  // Too far in the past
        assertFalse(new ApplicationTokenExpires(1409343309377L).isValid());  // Too far in the past
        assertFalse(new ApplicationTokenExpires(1709343309377L).isValid());  // Too far in the future
        assertFalse(new ApplicationTokenExpires("1709343309377").isValid());  // Too far in the future
    }

    @Test
    public void testOKApplicationTokenExpires() throws Exception {
        assertTrue(new ApplicationTokenExpires(100).isValid());
        assertTrue(new ApplicationTokenExpires(String.valueOf((System.currentTimeMillis()) + 300 * 1000)).isValid());
        log.debug(String.valueOf((System.currentTimeMillis()) + 5 * 30 * 24 * 60 * 60 * 1000));
        assertTrue(new ApplicationTokenExpires(1509445309377L).isValid());
        assertTrue(new ApplicationTokenExpires(23226566).isValid());
        assertTrue(new ApplicationTokenExpires("23226566").isValid());

    }

}