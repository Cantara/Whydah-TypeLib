package net.whydah.sso.ddd.model.sso;

import net.whydah.sso.ddd.model.application.ApplicationTokenExpires;
import net.whydah.sso.ddd.model.base.BaseLifespan;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTokenExpiresTest {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTokenExpiresTest.class);

    @Test
    public void testIllegalApplicationTokenExpires() throws Exception {
        assertFalse(ApplicationTokenExpires.isValid(-1));  // Negative delta does not give a meaning
        assertFalse(ApplicationTokenExpires.isValid("-1"));  // Negative delta does not give a meaning
        assertFalse(ApplicationTokenExpires.isValid(BaseLifespan.getDefaultTimeout(7)));  // Too high interval, must be not greater than 6 months
        assertFalse(ApplicationTokenExpires.isValid(String.valueOf((System.currentTimeMillis()) - 300 * 1000)));  // time in the past
        assertFalse(ApplicationTokenExpires.isValid("143247218654"));  // Too high interval
        assertFalse(ApplicationTokenExpires.isValid(140943309377L));  // Too far in the past
        assertFalse(ApplicationTokenExpires.isValid(1409343309377L));  // Too far in the past
        assertFalse(ApplicationTokenExpires.isValid(1709343309377L));  // Too far in the future
        assertFalse(ApplicationTokenExpires.isValid("1709343309377"));  // Too far in the future
    }

    @Test
    public void testOKApplicationTokenExpires() throws Exception {
        assertTrue(ApplicationTokenExpires.isValid(100));
        assertTrue(ApplicationTokenExpires.isValid(String.valueOf((System.currentTimeMillis()) + 300 * 1000)));
        log.debug(String.valueOf(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000L));
        log.debug(String.valueOf(System.currentTimeMillis()));
        log.debug("v:", String.valueOf(1525694265871L - 1512733927708L));
        //TODO: please check again this line       1500000000000L
        assertTrue(ApplicationTokenExpires.isValid(System.currentTimeMillis() + 60*60*24*1000));
        assertTrue(ApplicationTokenExpires.isValid(String.valueOf((System.currentTimeMillis()) + 300000 * 1000)));
        assertTrue(ApplicationTokenExpires.isValid(23226566));
        assertTrue(ApplicationTokenExpires.isValid("23226566"));
        //assertTrue(ApplicationTokenExpires.isValid("1515326423745"));

    }

}