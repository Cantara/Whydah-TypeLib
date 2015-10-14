package net.whydah.sso.application;

import net.whydah.sso.application.types.Application;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by totto on 30.06.15.
 */
public class ApplicationTest {

    private static final Logger log = getLogger(ApplicationTest.class);


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDefaultValuesInApplication() throws Exception {
        Application a = new Application("AppId", "appName");
        assertTrue("DEFCON5".equalsIgnoreCase(a.getSecurity().getMinDEFCON()));
        assertTrue("0".equalsIgnoreCase(a.getSecurity().getMinSecurityLevel()));
        assertTrue(Boolean.valueOf(a.getSecurity().getUserTokenFilter()));
    }

}