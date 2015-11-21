package net.whydah.sso.application;

import net.whydah.sso.application.helpers.ApplicationHelper;
import net.whydah.sso.application.helpers.ApplicationJsonpathHelper;
import net.whydah.sso.application.mappers.ApplicationMapper;
import net.whydah.sso.application.types.Application;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ApplicationJsonPathHelperTest {


    @Test
    public void testFindApplicationNameFromApplicationId() throws Exception {
        //System.out.println(ApplicationHelper.getDummyAppllicationListJson());
        String applicationName = ApplicationJsonpathHelper.findApplicationNameFromApplicationId(ApplicationHelper.getDummyAppllicationListJson(), "2201");
        System.out.println("ApplicationName: " + applicationName);
        assertTrue(applicationName.length() > 6);

    }

    @Test
    public void testVerifyApplicationsParsing() throws Exception {
        String applications[] = ApplicationJsonpathHelper.getApplicationNamesFromApplicationsJson(ApplicationHelper.getDummyAppllicationListJson());
        System.out.println("Found applications " + applications.length);
        System.out.println(ApplicationHelper.getDummyAppllicationListJson());
        assertTrue(7 < applications.length);
        assertTrue("ACS".equalsIgnoreCase(applications[0]));
        assertTrue("m2Circle".equalsIgnoreCase(applications[8]));
        for (String s : applications)
            System.out.println("ApplicationName: " + s);

    }

    @Test
    public void testVerifyApplicationsIDParsing() throws Exception {
        String applications[] = ApplicationJsonpathHelper.getApplicationIDsFromApplicationsJson(ApplicationHelper.getDummyAppllicationListJson());
        System.out.println("Found applications " + applications.length);
        System.out.println(ApplicationHelper.getDummyAppllicationListJson());
        assertTrue(7 < applications.length);
        for (String s : applications) {
            System.out.println("ApplicationIDs: " + s);
            System.out.println("Applicationsecrets: " + ApplicationJsonpathHelper.findApplicationSecretFromApplicationListById(ApplicationHelper.getDummyAppllicationListJson(), s));
        }

    }

    @Test
    public void testVerifyApplicationIDParsing() throws Exception {
        Application applicationFromJson = ApplicationMapper.fromJson(ApplicationHelper.getDummyApplicationJson());
        System.out.println(ApplicationMapper.toPrettyJson(applicationFromJson));
        System.out.println("Applicationsecret: " + ApplicationJsonpathHelper.findApplicationSecretFromApplicationId(ApplicationHelper.getDummyApplicationJson(), applicationFromJson.getId()));

    }

}
