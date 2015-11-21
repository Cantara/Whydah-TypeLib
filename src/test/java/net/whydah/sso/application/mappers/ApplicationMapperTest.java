package net.whydah.sso.application.mappers;

import net.whydah.sso.application.types.Application;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by baardl on 21.11.15.
 */
public class ApplicationMapperTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFromJson() throws Exception {
        Application application = ApplicationMapper.fromJson(jsonWithExtras);
        assertNotNull(application);

    }

    private String jsonWithExtras = "{\"id\":\"ae8ef531-2ab5-4d0f-ae35-d77a56915094\",\"name\":\"postjson\",\"description\":\"postjson\",\"applicationUrl\":null,\"logoUrl\":null,\"roles\":null,\"defaultRoleName\":\"postjson\",\"defaultOrganizationName\":\"postjson\",\"security\":{\"minSecurityLevel\":\"0\",\"minDEFCON\":\"DEFCON5\",\"maxSessionTimoutSeconds\":\"86400\",\"allowedIpAddresses\":[\"0.0.0.0/0\"],\"userTokenFilter\":\"true\",\"secret\":\"postjsonpostjsonpostjson\"},\"acl\":null,\"organizationNames\":null,\"secret\":\"postjsonpostjsonpostjson\"}";
}