package net.whydah.sso.user;

import net.whydah.sso.user.mappers.UserRoleMapper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class UserRoleMapperTest {

    @Test
    public void testToJsonOnNullRole() throws Exception {

        UserApplicationRoleEntry userApplicationRoleEntry = null;
        String json = UserRoleMapper.toJson(userApplicationRoleEntry);
        System.out.println("json:" + json);
        assertTrue("{\"orgName\":\"\",\"roleValue\":\"\",\"roleName\":\"\",\"applicationId\":\"\",\"applicationName\":\"\"}".equalsIgnoreCase(json));
    }

    @Test
    public void testToJsonOnEmptyRole() throws Exception {

        UserApplicationRoleEntry userApplicationRoleEntry = new UserApplicationRoleEntry();
        String json = UserRoleMapper.toJson(userApplicationRoleEntry);
        System.out.println("json:" + json);
        assertTrue("{\"orgName\":\"\",\"roleValue\":\"\",\"roleName\":\"\",\"applicationId\":\"\"}".equalsIgnoreCase(json));
    }

    @Test
    public void testToJsonOnEmptyRoleList() throws Exception {

        List<UserApplicationRoleEntry> roles = new LinkedList<>();
        String json = UserRoleMapper.toJson(roles);
        System.out.println("json:" + json);
        assertTrue("[]".equalsIgnoreCase(json));
    }

    @Test
    public void testToJsonOnRoleList() throws Exception {

        List<UserApplicationRoleEntry> roles = new LinkedList<>();
        UserApplicationRoleEntry userApplicationRoleEntry = new UserApplicationRoleEntry();
        roles.add(userApplicationRoleEntry);
        String json = UserRoleMapper.toJson(roles);
        System.out.println("json:" + json);
        assertTrue("[{\"orgName\":\"\",\"roleValue\":\"\",\"roleName\":\"\",\"applicationId\":\"\"}]".equalsIgnoreCase(json));
    }

    @Test
    public void testToJsonOnRoleListCornerCase() throws Exception {

        List<UserApplicationRoleEntry> roles = new LinkedList<>();
        UserApplicationRoleEntry userApplicationRoleEntry = new UserApplicationRoleEntry();
        userApplicationRoleEntry.setRoleValue("22");
        userApplicationRoleEntry.setRoleName("hjhk");
        userApplicationRoleEntry.setOrgName("dsfsdf");
        roles.add(userApplicationRoleEntry);
        String json = UserRoleMapper.toJson(roles);
        System.out.println("json:" + json);
        assertTrue("[{\"orgName\":\"dsfsdf\",\"roleValue\":\"22\",\"roleName\":\"hjhk\",\"applicationId\":\"\"}]".equalsIgnoreCase(json));
    }

}
