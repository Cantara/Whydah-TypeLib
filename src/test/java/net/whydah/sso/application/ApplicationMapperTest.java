package net.whydah.sso.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.application.helpers.ApplicationHelper;
import net.whydah.sso.application.mappers.ApplicationMapper;
import net.whydah.sso.application.types.Application;
import net.whydah.sso.application.types.ApplicationACL;
import net.whydah.sso.application.types.ApplicationAvailableOrganizationNames;
import net.whydah.sso.application.types.ApplicationAvailableRoleNames;
import net.whydah.sso.whydah.UserSessionSecurityLevel;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:erik-dev@fjas.no">Erik Drolshammer</a> 2015-06-30
 */
public class ApplicationMapperTest {
    private static final Logger log = LoggerFactory.getLogger(ApplicationMapperTest.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static Application app1;

    @BeforeClass
    public static void initTestData() {
        app1 = new Application("appId1", "applicationName1");
        app1.setDescription("description of application");
        app1.setApplicationUrl("https://webtest.exapmle.com");
        app1.setLogoUrl("https://webtest.example.com/test.png");
        app1.addRole(new ApplicationAvailableRoleNames("roleId1", "roleName1"));
        app1.addOrganizationName(new ApplicationAvailableOrganizationNames("orgId", "organizationName1"));
        app1.setDefaultRoleName("defaultRoleName");
        app1.setDefaultRoleName("roleName1");
        app1.setDefaultOrganizationName("defaultOrgName");
        app1.addOrganizationName(new ApplicationAvailableOrganizationNames("orgidxx", app1.getDefaultOrganizationName()));
        app1.addAcl(new ApplicationACL("11", "/user", "READ"));

        app1.getSecurity().setSecret("veryVerySecret");
        
    }

    @Test
    public void testToFromJEmptyson() throws Exception {


        String emptyList = "{}";
        List<Application> applicationFromJson = ApplicationMapper.fromJsonList(emptyList);
        assertTrue(applicationFromJson.isEmpty());
    }

    @Test
    public void testToFromJson() throws Exception {

        log.trace(ApplicationMapper.toJson(app1));

        String indented = ApplicationMapper.toPrettyJson(app1);
        log.debug("\n" + indented);
        Application applicationFromJson = ApplicationMapper.fromJson(indented);
        assertEquals(app1, applicationFromJson);
        
        indented = indented.replaceAll("\"userTokenFilter\" : true", "\"userTokenFilter\" : \"true\"");
        log.debug("\n KE KA" + indented);
        applicationFromJson = ApplicationMapper.fromJson(indented);
        assertEquals(app1, applicationFromJson);
    }
    
    @Test
    public void testFullTokenValidation() throws Exception {

        app1.getSecurity().setUserTokenFilter("false");
        assertTrue(app1.isFullTokenApplication());
        

        app1.getSecurity().setUserTokenFilter("true");
        assertFalse(app1.isFullTokenApplication());
        

    }

    @Test
    public void testImportApplications() throws Exception {

        String json = IOUtils.toString(this.getClass().getResourceAsStream("/testapplications.json"), "UTF-8");
        json = json.replace("\uFEFF", "");
        List<Application> importList = ApplicationMapper.fromJsonList(json);
        List<String> allUserNames = new ArrayList<String>();
        for (Application ua : importList) {
            allUserNames.add("\"" + ua.getName() + "\"");
        }
    }

    @Test
    public void testImportMoreApplications() throws Exception {

        String json = IOUtils.toString(this.getClass().getResourceAsStream("/applications.json"), "UTF-8");
        json = json.replace("\uFEFF", "");
        List<Application> importList = ApplicationMapper.fromJsonList(json);
        List<String> allUserNames = new ArrayList<String>();
        for (Application ua : importList) {
            allUserNames.add("\"" + ua.getName() + "\"");
        }
    }

    @Test
    public void testToApplicationList() throws Exception {
        Application app2 = new Application("appId2", "applicationName2");
        String json = ApplicationMapper.toJson(Arrays.asList(new Application[]{app1, app2}));
        String indented = ApplicationMapper.toPrettyJson(Arrays.asList(new Application[]{app1, app2}));
        log.debug("\n" + indented);

        List<Application> applications = ApplicationMapper.fromJsonList(json);
        assertEquals(applications.size(), 2);

        log.debug("short list\n" + ApplicationMapper.toShortListJson(app1));
        log.debug("short list\n" + ApplicationMapper.toJson(app1));
    }


    @Test
    public void fromRealJson() throws Exception{
        Application applicationFromJson = ApplicationMapper.fromJson(ApplicationHelper.getDummyApplicationJson());
        String jsonString = ApplicationMapper.toPrettyJson(applicationFromJson);
        log.debug(jsonString);
        Application applicationBackFromJson = ApplicationMapper.fromJson(jsonString);
        assertEquals(applicationFromJson, applicationBackFromJson);
    }

    @Test
    public void fromRealJson2() throws Exception {
        log.trace(ApplicationMapper.toJson(app1));

        Application applicationFromJson = ApplicationMapper.fromJson(ApplicationMapper.toJson(app1));
        String jsonString = ApplicationMapper.toPrettyJson(applicationFromJson);
        log.debug(jsonString);
        Application application2 = mapper.readValue(jsonString, Application.class);
        ;
        String json2String = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(application2);
        log.debug(json2String);
        Application applicationFromJson2 = ApplicationMapper.fromJson(json2String);
        List<UserSessionSecurityLevel> userSessionSecurityLevels = new LinkedList<>();
        userSessionSecurityLevels.add(UserSessionSecurityLevel.LEVEL0);
        userSessionSecurityLevels.add(UserSessionSecurityLevel.LEVEL1);
        application2.setSupportedUserSessionLevelList(userSessionSecurityLevels);

        String json3String = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(application2);
        log.debug(json3String);
        Application applicationFromJson3 = ApplicationMapper.fromJson(json3String);
        log.debug(applicationFromJson3.toString());
    }


    @Test
    public  void jsonWithOnlyRequiredParameters() throws Exception {
        Application app = ApplicationMapper.fromJson(ApplicationHelper.getDummyApplicationOnlyRequiredParameters());
        assertNotNull(app);
        log.debug("App: {}", app.toString() );
    }

    @Test
    public  void toSafeJsonWithOnlyRequiredParameters() throws Exception {
        Application app = ApplicationMapper.fromJson(ApplicationHelper.getDummyApplicationOnlyRequiredParameters());
        assertNotNull(app);
        String jsonString = ApplicationMapper.toSafeJson(app);
        assertNotNull(jsonString);
        log.debug("Json String: {}", jsonString );
    }

    @Test
    public void fromRealJsonList() throws Exception{
        List<Application> applications = ApplicationMapper.fromJsonList(ApplicationHelper.getDummyAppllicationListJson());
        log.trace(ApplicationHelper.getDummyAppllicationListJson());
        for (Application application : applications) {
            log.debug("Safe json" + ApplicationMapper.toSafeJson(application));
            assertTrue(ApplicationMapper.fromJson(ApplicationMapper.toSafeJson(application)).getSecurity().getSecret().equalsIgnoreCase("*************"));
            assertTrue(ApplicationMapper.fromJson(ApplicationMapper.toSafeJson(application)).getSecurity().getAllowedIpAddresses() == null);
            log.debug("Short Json" + ApplicationMapper.toShortListJson(application));
            log.debug("ToJson" + ApplicationMapper.toJson(application));
        }

        log.debug("Safe list:" + ApplicationMapper.toSafeJson(applications));
    }


    @Test
    public void testFromJson() throws Exception {
        Application application = ApplicationMapper.fromJson(jsonWithExtras);
        assertNotNull(application);

    }

    private String jsonWithExtras = "{\"id\":\"ae8ef531-2ab5-4d0f-ae35-d77a56915094\",\"name\":\"postjson\",\"description\":\"postjson\",\"applicationUrl\":null,\"logoUrl\":null,\"roles\":null,\"defaultRoleName\":\"postjson\",\"defaultOrganizationName\":\"postjson\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"postjsonpostjsonpostjson\"},\"acl\":null,\"organizationNames\":null,\"secret\":\"postjsonpostjsonpostjson\"}";

    private String jsonList = "[{\"id\":\"2219\",\"name\":\"Whydah-UserAdminWebApp\",\"description\":\"The back-office User Administration module of the Whydah IAM/SSO\",\"company\":\"Cantara\",\"tags\":\"JURISDICTION_NORWAY\",\"applicationUrl\":\"https://whydahdev.cantara.no/useradmin/\",\"logoUrl\":\"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\"fullTokenApplication\":false,\"roles\":[{\"id\":\"why101\",\"name\":\"WhydahAdministrator\"},{\"id\":\"why102\",\"name\":\"ApplicationAdministrator\"},{\"id\":\"why103\",\"name\":\"Application\"},{\"id\":\"why104\",\"name\":\"UserDirectoryAdministrator\"}],\"defaultRoleName\":\"WhydahAdministrator\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}],\"defaultOrganizationName\":\"Whydah\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"9EH5u5wJFKsUvJFmhypwK7j6D\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}]},{\"id\":\"2210\",\"name\":\"Whydah-UserIdentityBackend\",\"description\":\"The UserIdentityBackend module of the Whydah IAM/SSO\",\"company\":\"Cantara\",\"tags\":\"HIDDEN, JURISDICTION_NORWAY\",\"applicationUrl\":\"https://whydahdev.cantara.no/uib/\",\"logoUrl\":\"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\"fullTokenApplication\":false,\"roles\":[{\"id\":\"why101\",\"name\":\"WhydahAdministrator\"},{\"id\":\"why102\",\"name\":\"ApplicationAdministrator\"},{\"id\":\"why103\",\"name\":\"Application\"},{\"id\":\"why104\",\"name\":\"UserDirectoryAdministrator\"}],\"defaultRoleName\":\"Application\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}],\"defaultOrganizationName\":\"Whydah\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"6r46g3q986Ep6By7B9J46m96D\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}]},{\"id\":\"2212\",\"name\":\"Whydah-UserAdminService\",\"description\":\"Responsible for configuring which API/useradministration services Whydah IAM/SSO should provide for 3rd parties (outside the innermost firewall)\",\"company\":\"Cantara\",\"tags\":\"HIDDEN, JURISDICTION_NORWAY\",\"applicationUrl\":\"https://whydahdev.cantara.no/uas/\",\"logoUrl\":\"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\"fullTokenApplication\":false,\"roles\":[{\"id\":\"why101\",\"name\":\"User\"},{\"id\":\"why102\",\"name\":\"Application\"}],\"defaultRoleName\":\"Application\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}],\"defaultOrganizationName\":\"Whydah\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"9ju592A4t8dzz8mz7a5QQJ7Px\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}]},{\"id\":\"101\",\"name\":\"ACSResource\",\"description\":\"Finn den kompetansen du trenger, når du trenger det. Lag eksklusive CV'er tillpasset leseren.\",\"company\":\"Norway AS\",\"tags\":\"JURISDICTION_NORWAY\",\"applicationUrl\":null,\"logoUrl\":null,\"fullTokenApplication\":false,\"roles\":[{\"id\":\"acs101\",\"name\":\"Employee\"},{\"id\":\"acs102\",\"name\":\"Manager\"},{\"id\":\"acs103\",\"name\":\"Administrator\"}],\"defaultRoleName\":\"Employee\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"GetWhydah\"},{\"id\":\"103\",\"name\":\"ACSOrganization\"}],\"defaultOrganizationName\":\"ACSOrganization\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"55fhRM6nbKZ2wfC6RMmMuzXpk\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"GetWhydah\"},{\"id\":\"103\",\"name\":\"ACSOrganization\"}]},{\"id\":\"2299\",\"name\":\"Whydah-TestWebApplication\",\"description\":\"The different example webapps to explain how to use/integrate with the Whydah IAM/SSO\",\"company\":\"Teknologihuset\",\"tags\":\"JURISDICTION_NORWAY\",\"applicationUrl\":\"https://whydahdev.cantara.no/test/\",\"logoUrl\":\"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\"fullTokenApplication\":false,\"roles\":[{\"id\":\"roleId1\",\"name\":\"Whydah-TestWebApplication-user\"}],\"defaultRoleName\":\"Whydah-TestWebApplication-user\",\"orgs\":[{\"id\":\"orgId\",\"name\":\"organizationName1\"},{\"id\":\"orgidxx\",\"name\":\"defaultOrgName\"}],\"defaultOrganizationName\":\"Whydah\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"33879936R6Jr47D4Hj5R6p9qT\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"orgId\",\"name\":\"organizationName1\"},{\"id\":\"orgidxx\",\"name\":\"defaultOrgName\"}]},{\"id\":\"2211\",\"name\":\"Whydah-SecurityTokenService\",\"description\":\"The ApplicationToken and UserToken module in Whydah IAM/SSO\",\"company\":\"Cantara\",\"tags\":\"HIDDEN, JURISDICTION_NORWAY\",\"applicationUrl\":\"https://whydahdev.cantara.no/sts/\",\"logoUrl\":\"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\"fullTokenApplication\":false,\"roles\":[{\"id\":\"why101\",\"name\":\"User\"},{\"id\":\"why102\",\"name\":\"Application\"}],\"defaultRoleName\":\"Application\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}],\"defaultOrganizationName\":\"Whydah\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"6r46g3q986Ep6By7B9J46m96D\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}]},{\"id\":\"100\",\"name\":\"ACS\",\"description\":\"Finn den kompetansen du trenger, når du trenger det. Lag eksklusive CV'er tillpasset leseren.\",\"company\":\"Norway AS\",\"tags\":\"HIDDEN, JURISDICTION_NORWAY\",\"applicationUrl\":null,\"logoUrl\":null,\"fullTokenApplication\":false,\"roles\":[{\"id\":\"acs101\",\"name\":\"Employee\"},{\"id\":\"acs102\",\"name\":\"Manager\"},{\"id\":\"acs103\",\"name\":\"Administrator\"}],\"defaultRoleName\":\"Employee\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}],\"defaultOrganizationName\":\"ACSOrganization\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"45fhRM6nbKZ2wfC6RMmMuzXpk\"},\"acl\":[{\"applicationId\":\"11\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}]},{\"id\":\"2215\",\"name\":\"Whydah-SSOLoginWebApp\",\"description\":\"The SSO / WebFrontend module of the Whydah IAM/SSO\",\"company\":\"Cantara\",\"tags\":\"HIDDEN, JURISDICTION_NORWAY\",\"applicationUrl\":\"https://whydahdev.cantara.no/sso/\",\"logoUrl\":\"https://whydahdev.cantara.no/useradmin/img/whydah.png\",\"fullTokenApplication\":false,\"roles\":[{\"id\":\"why101\",\"name\":\"SSOApplication\"}],\"defaultRoleName\":\"SSOApplication\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}],\"defaultOrganizationName\":\"Whydah\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"33779936R6Jr47D4Hj5R6p9qT\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}]},{\"id\":\"2001\",\"name\":\"m2Circle\",\"description\":null,\"company\":\"Auke AS\",\"tags\":\"SECURE CHAT\",\"applicationUrl\":null,\"logoUrl\":null,\"fullTokenApplication\":false,\"roles\":[{\"id\":\"roleId1\",\"name\":\"roleName1\"}],\"defaultRoleName\":\"member\",\"orgs\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}],\"defaultOrganizationName\":\"Whydah\",\"security\":{\"minSecurityLevel\":\"0\",\"minimumDEFCONLevel\":\"DEFCON5\",\"maxSessionTimeoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"YKHH54bNpnvQEF2vCJSWtctB\"},\"acl\":[{\"applicationId\":\"2211\",\"applicationACLPath\":\"/user\",\"accessRights\":null}],\"organizationNames\":[{\"id\":\"100\",\"name\":\"Whydah\"},{\"id\":\"101\",\"name\":\"Cantara\"},{\"id\":\"102\",\"name\":\"Getwhydah\"}]}]";

    @Test
    public void testFromJsonListR() throws Exception {
        List<Application> applications = ApplicationMapper.fromJsonList(jsonList);
        assertNotNull(applications);

    }
    
    
}
