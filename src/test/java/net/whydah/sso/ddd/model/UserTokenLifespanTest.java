package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTokenLifespanTest {


    private static final Logger log = LoggerFactory.getLogger(UserTokenLifespanTest.class);

    @Test
    public void testIllegalUserTokenLifespan() throws Exception {
        assertFalse(UserTokenLifespan.isValid(-1));  // Negative delta does not give a meaning
        assertFalse(UserTokenLifespan.isValid("-1"));  // Negative delta does not give a meaning
        assertFalse(UserTokenLifespan.isValid(String.valueOf((System.currentTimeMillis()) - 300 * 1000)));  // time in the past
        assertFalse(UserTokenLifespan.isValid("1432472186"));  // Too high interval
        assertFalse(UserTokenLifespan.isValid(140943309377L));  // Too far in the past
        assertFalse(UserTokenLifespan.isValid(1409343309377L));  // Too far in the past
        assertFalse(UserTokenLifespan.isValid(1709343309377L));  // Too far in the future
        assertFalse(UserTokenLifespan.isValid("1709343309377"));  // Too far in the future
        assertFalse(UserTokenLifespan.isValid(432472186));  // Too high interval
    }

    @Test
    public void testOKUserTokenLifespan() throws Exception {
        assertTrue(UserTokenLifespan.isValid(100));
        assertTrue(UserTokenLifespan.isValid(String.valueOf((System.currentTimeMillis()) + 300 * 1000)));
        log.debug(String.valueOf((System.currentTimeMillis()) + 5 * 30 * 24 * 60 * 60 * 1000));
        //TODO: please check again this line
        //assertTrue(UserTokenLifespan.isValid(1509445309377L));
        assertTrue(UserTokenLifespan.isValid(23226566));
        log.debug("23226566-" + String.valueOf(new UserTokenLifespan("23226566").getMillisecondValue()));
        assertTrue("23226566".equalsIgnoreCase(String.valueOf(new UserTokenLifespan("23226566").getMillisecondValue())));
        assertTrue(UserTokenLifespan.isValid("23226566"));

    }


}