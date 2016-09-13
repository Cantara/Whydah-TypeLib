package net.whydah.sso.application;

import net.whydah.sso.application.helpers.ApplicationHelper;
import net.whydah.sso.application.helpers.ApplicationJsonpathHelper;
import net.whydah.sso.application.mappers.ApplicationMapper;
import net.whydah.sso.application.types.Application;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ApplicationJsonPathHelperTest {

    private final static Logger log = LoggerFactory.getLogger(ApplicationJsonPathHelperTest.class);

    @Test
    public void testFindApplicationNameFromApplicationId() throws Exception {
        String applicationName = ApplicationJsonpathHelper.findApplicationNameFromApplicationId(ApplicationHelper.getDummyAppllicationListJson(), "2210");
        log.trace("ApplicationName: " + applicationName);
        assertTrue(applicationName.length() > 6);

    }

    @Test
    public void testVerifyApplicationsParsing() throws Exception {
        String applications[] = ApplicationJsonpathHelper.getApplicationNamesFromApplicationsJson(ApplicationHelper.getDummyAppllicationListJson());
        log.trace("Found applications " + applications.length);
        log.trace(ApplicationHelper.getDummyAppllicationListJson());
        assertTrue(7 < applications.length);
        assertTrue("ACS".equalsIgnoreCase(applications[0]));
        assertTrue("m2Circle".equalsIgnoreCase(applications[8]));
        for (String s : applications)
            log.trace("ApplicationName: " + s);

    }

    @Test
    public void testVerifyApplicationsIDParsing() throws Exception {
        String applications[] = ApplicationJsonpathHelper.getApplicationIDsFromApplicationsJson(ApplicationHelper.getDummyAppllicationListJson());
        log.trace("Found applications " + applications.length);
        log.trace(ApplicationHelper.getDummyAppllicationListJson());
        assertTrue(6 < applications.length);
        for (String s : applications) {
            log.trace("ApplicationIDs: " + s);
            log.trace("Applicationsecrets: " + ApplicationJsonpathHelper.findApplicationSecretFromApplicationListById(ApplicationHelper.getDummyAppllicationListJson(), s));
        }

    }

    @Test
    public void testVerifyApplicationIDParsing() throws Exception {
        Application applicationFromJson = ApplicationMapper.fromJson(ApplicationHelper.getDummyApplicationJson());
        log.trace(ApplicationMapper.toPrettyJson(applicationFromJson));
        log.trace("Applicationsecret: " + ApplicationJsonpathHelper.findApplicationSecretFromApplicationId(ApplicationHelper.getDummyApplicationJson(), applicationFromJson.getId()));

    }

}
