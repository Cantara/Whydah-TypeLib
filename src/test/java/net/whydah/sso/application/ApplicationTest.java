package net.whydah.sso.application;

import net.whydah.sso.application.helpers.ApplicationHelper;
import net.whydah.sso.application.mappers.ApplicationMapper;
import net.whydah.sso.application.types.Application;
import net.whydah.sso.basehelpers.JsonPathHelper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

public class ApplicationTest {

    private static final Logger log = getLogger(ApplicationTest.class);


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDefaultValuesInApplication() throws Exception {
        Application a = new Application("AppId", "appName");
        a.setFullTokenApplication("true");
        System.out.println(ApplicationMapper.toPrettyJson(a));
        assertTrue("DEFCON5".equalsIgnoreCase(a.getSecurity().getMinDEFCON()));
        assertTrue("0".equalsIgnoreCase(a.getSecurity().getMinSecurityLevel()));
        assertTrue(Boolean.valueOf(a.getSecurity().getUserTokenFilter()));
    }

    @Test
    public void testValidFullTokenApplications() {
        List<Application> applications = ApplicationMapper.fromJsonList(ApplicationHelper.getDummyAppllicationListJson());
        for (Application application : applications) {
            log.debug("is fulltoken {} appid:{}", application.isFullTokenApplication(), application.getId());

        }
    }


    @Test
    public void testGetParameterForApplication() {
        String param = "$.security.minSecurityLevel";
        String applicationID = "2211";
        List<Application> applications = ApplicationMapper.fromJsonList(ApplicationHelper.getDummyAppllicationListJson());
        for (Application application : applications) {
            if (applicationID.equalsIgnoreCase(application.getId())) {
                log.info("Found application {}, looking for ", ApplicationMapper.toPrettyJson(application), param);
                System.out.println(JsonPathHelper.findJsonPathValue(ApplicationMapper.toPrettyJson(application), param));

            }
        }

    }

}