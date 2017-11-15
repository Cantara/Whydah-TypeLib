package net.whydah.sso.ddd.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationUrlTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationUrlTest.class);

    @Test
    public void testIllegalApplicationUrl() {
        assertFalse(ApplicationUrl.isValid("httpa://whydahdev.cantara.no"));
        assertFalse(ApplicationUrl.isValid("-1"));  // Negative delta does not give a meaning
        assertFalse(ApplicationUrl.isValid(String.valueOf((System.currentTimeMillis()) - 300 * 1000)));  // time in the past
        assertFalse(ApplicationUrl.isValid("1432472186"));  // Too high interval
        assertFalse(ApplicationUrl.isValid("1709343309377"));  // Too far in the future
    }

    @Test
    public void testOKApplicationUrl() {
        assertTrue(ApplicationUrl.isValid("http://whydahdev.cantara.no"));
        assertTrue(ApplicationUrl.isValid("https://whydahdev.cantara.no"));
        assertTrue(ApplicationUrl.isValid("https://myUrl.com"));
        assertTrue(ApplicationUrl.isValid("https://myUrl.com/"));
        assertTrue(ApplicationUrl.isValid("https://myUrl.com/testpage"));

    }

}