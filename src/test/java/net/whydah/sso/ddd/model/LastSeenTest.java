package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LastSeenTest {

    private static final Logger log = LoggerFactory.getLogger(LastSeenTest.class);

    @Test
    public void testIllegalLastSeen() throws Exception {
        assertFalse(LastSeen.isValid(100));  // Time in the future
        assertFalse(LastSeen.isValid(432472186));  // Too high interval
        assertFalse(LastSeen.isValid(String.valueOf((System.currentTimeMillis()) + 300 * 1000)));  // time in the future
        assertFalse(LastSeen.isValid("1432472186"));  // Too high interval
        assertFalse(LastSeen.isValid(140943309377L));  // Too far in the past
        assertFalse(LastSeen.isValid(1409343309377L));  // Too far in the past
        assertFalse(LastSeen.isValid(1709343309377L));  // Too far in the future
        assertFalse(LastSeen.isValid("1709343309377"));  // Too far in the future
        assertFalse(LastSeen.isValid(-1));  // Negative delta does not give a meaning
        assertFalse(LastSeen.isValid("-1"));  // Negative delta does not give a meaning
    }

    @Test
    public void testOKLastSeen() throws Exception {
        assertTrue(LastSeen.isValid(String.valueOf((System.currentTimeMillis()) - 300 * 1000)));  // Time in the past is OK
        assertTrue(LastSeen.isValid(String.valueOf((System.currentTimeMillis()) - 3000 * 1000)));  // Time in the past is OK
        assertTrue(LastSeen.isValid(String.valueOf((System.currentTimeMillis()) - 30000 * 1000)));  // Time in the past is OK
        new LastSeen("Wed Nov 15 22:08:41 CET 2017");
        assertTrue(LastSeen.isValid("Wed Nov 15 22:08:41 CET 2017"));  // Time in the past is OK
        LastSeen lastSeen = new LastSeen("Not Seen");
        assertTrue(LastSeen.isValid("Not Seen"));

    }

}