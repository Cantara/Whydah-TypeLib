package net.whydah.sso.user;

import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.user.helpers.UserRoleJsonPathHelper;
import net.whydah.sso.user.helpers.UserRoleXpathHelper;
import net.whydah.sso.user.mappers.UserRoleMapper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;

import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

public class UserRoleTest {

    private static final Logger log = getLogger(UserRoleTest.class);


    @Test
    public void testFromJson() throws Exception {

        UserApplicationRoleEntry role = UserRoleJsonPathHelper.getUserRoleFromJson(roleJson);
        log.debug("roleId" + JsonPathHelper.getJsonArrayFromJsonpathExpression(roleJson, "$..roleId").toJSONString());
        log.debug("Test:" + role);
        log.debug("Test:" + UserRoleMapper.toJson(role));
        assertNotNull(role);

    }

    private String roleJson = "[\n" +
            "    {\n" +
            "        \"roleId\": \"8bafa279-1615-4833-869e-106daaefa797\",\n" +
            "        \"uid\": \"e365cccc-dc2b-4c79-a79c-d0ae3b79a45d\",\n" +
            "        \"applicationId\": \"201\",\n" +
            "        \"applicationRoleName\": \"testRoleName\",\n" +
            "        \"applicationRoleValue\": \"true\",\n" +
            "        \"applicationName\": \"DomainConfig\",\n" +
            "        \"organizationName\": \"testOrg\"\n" +
            "    }\n" +
            "]";


    private String uawaRoleJson = "{\"applicationRoleName\":\"test\",\"applicationId\":\"100\",\"applicationName\":\"\",\"organizationName\":\"hj\"}";

    @Test
    public void testMapperFromUAWAJson() throws Exception {

        UserApplicationRoleEntry entry = UserRoleMapper.fromJson(uawaRoleJson);
        assertTrue(entry.getRoleValue().equals(""));
    }
}
