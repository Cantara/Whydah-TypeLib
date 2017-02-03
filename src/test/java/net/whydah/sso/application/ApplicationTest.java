package net.whydah.sso.application;

import net.whydah.sso.application.helpers.ApplicationHelper;
import net.whydah.sso.application.mappers.ApplicationMapper;
import net.whydah.sso.application.types.Application;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.whydah.DEFCON;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.List;

import static org.junit.Assert.*;
import static org.slf4j.LoggerFactory.getLogger;

public class ApplicationTest {

    private static final Logger log = getLogger(ApplicationTest.class);


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDefaultValuesInApplication() throws Exception {
        Application a = new Application("AppId", "appName");
        log.debug(ApplicationMapper.toPrettyJson(a));
        assertTrue(DEFCON.DEFCON5 == a.getSecurity().getMinimumDEFCONLevel());
        assertTrue(0 == a.getSecurity().getMinSecurityLevel());
        assertTrue(Boolean.valueOf(a.getSecurity().getUserTokenFilter()));
        assertFalse(a.isFullTokenApplication());
    }

    @Test
    public void testValidFullTokenApplications() {
        List<Application> applications = ApplicationMapper.fromJsonList(ApplicationHelper.getDummyAppllicationListJson());
        for (Application application : applications) {
            log.debug("has usertokenfilter {} appid:{}", Boolean.valueOf(application.getSecurity().getUserTokenFilter()), application.getId());

        }
    }


    @Test
    public void testGetParameterForApplication_minSecurityLevel() {
        String param = "$.security.minSecurityLevel";
        String applicationID = "2211";
        List<Application> applications = ApplicationMapper.fromJsonList(ApplicationHelper.getDummyAppllicationListJson());
        for (Application application : applications) {
            if (applicationID.equalsIgnoreCase(application.getId())) {
                log.info("Found application {}, looking for {}", ApplicationMapper.toPrettyJson(application), param);
                log.trace(JsonPathHelper.findJsonPathValue(ApplicationMapper.toPrettyJson(application), param));

            }
        }

    }

    @Test
    public void testGetParameterForApplication_maxSessionTimeoutSeconds() {
        String param = "$.security.maxSessionTimeoutSeconds";
        String applicationID = "2211";
        List<Application> applications = ApplicationMapper.fromJsonList(ApplicationHelper.getDummyAppllicationListJson());
        for (Application application : applications) {
            if (applicationID.equalsIgnoreCase(application.getId())) {
                log.info("Found application {}, looking for {}", ApplicationMapper.toJson(application), param);
                log.trace(JsonPathHelper.findJsonPathValue(ApplicationMapper.toPrettyJson(application), param));

            }
        }

    }

    @Test
    public void testIfSecurityGetsMissing() {
        String applicationJson = "{\n" +
                "  \"id\": \"100\",\n" +
                "  \"name\": \"ACS\",\n" +
                "  \"description\": \"Finn den kompetansen du trenger, når du trenger det. Lag eksklusive CV'er tillpasset leseren.\",\n" +
                "  \"company\": \"Norway AS\",\n" +
                "  \"tags\": \"HIDDEN, JURISDICTION_NORWAY\",\n" +
                "  \"applicationUrl\": null,\n" +
                "  \"logoUrl\": null,\n" +
                "  \"roles\": [\n" +
                "    {\n" +
                "      \"id\": \"acs101\",\n" +
                "      \"name\": \"Employee\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"acs102\",\n" +
                "      \"name\": \"Manager\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"acs103\",\n" +
                "      \"name\": \"Administrator\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"defaultRoleName\": \"Employee\",\n" +
                "  \"orgs\": [\n" +
                "    {\n" +
                "      \"id\": \"100\",\n" +
                "      \"name\": \"Whydah\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"101\",\n" +
                "      \"name\": \"Cantara\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"102\",\n" +
                "      \"name\": \"Getwhydah\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"defaultOrganizationName\": \"ACSOrganization\",\n" +
                "  \"security\": {\n" +
                "    \"minSecurityLevel\": \"0\",\n" +
                "    \"minDEFCON\": \"DEFCON5\",\n" +
                "    \"maxSessionTimeoutSeconds\": \"86400\",\n" +
                "    \"allowedIpAddresses\": [\n" +
                "      \"0.0.0.0/0\"\n" +
                "    ],\n" +
                "    \"userTokenFilter\": \"true\",\n" +
                "    \"secret\": \"45fhRM6nbKZ2wfC6RMmMuzXpk\"\n" +
                "  },\n" +
                "  \"acl\": [\n" +
                "    {\n" +
                "      \"applicationId\": \"11\",\n" +
                "      \"applicationACLPath\": \"/user\",\n" +
                "      \"accessRights\": null\n" +
                "    }\n" +
                "  ],\n" +
                "  \"organizationNames\": [\n" +
                "    {\n" +
                "      \"id\": \"100\",\n" +
                "      \"name\": \"Whydah\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"101\",\n" +
                "      \"name\": \"Cantara\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"102\",\n" +
                "      \"name\": \"Getwhydah\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"secret\": \"45fhRM6nbKZ2wfC6RMmMuzXpk\"\n" +
                "}";
        Application testApp = ApplicationMapper.fromJson(applicationJson);
        assertNotNull(testApp.getSecurity());
    }


}