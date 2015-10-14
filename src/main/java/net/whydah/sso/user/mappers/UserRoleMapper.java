package net.whydah.sso.user.mappers;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import net.whydah.sso.user.helpers.UserXpathHelper;
import net.whydah.sso.user.types.UserRoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;

public class UserRoleMapper {

    public static final Logger log = LoggerFactory.getLogger(UserRoleMapper.class);
    public static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    public static UserRoleVO fromXml(String roleXml) {

        String id = UserXpathHelper.findValue(roleXml, "/application/id");
        String userId = UserXpathHelper.findValue(roleXml, "/application/uid");
        String appId = UserXpathHelper.findValue(roleXml, "/application/appId");
        String appName = UserXpathHelper.findValue(roleXml, "/application/applicationName");
        String orgName = UserXpathHelper.findValue(roleXml, "/application/orgName");
        String roleName = UserXpathHelper.findValue(roleXml, "/application/roleName");
        String roleValue = UserXpathHelper.findValue(roleXml, "/application/roleValue");
        UserRoleVO userRole = new UserRoleVO(null, appId, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        return userRole;
    }




    public static String toJson(UserRoleVO userrole) {
        String json = "{";
        if (isNotEmpty(userrole.getId())) {
            json = json + "\"roleId\":\"" + userrole.getId() + "\",";
        }
        if (isNotEmpty(userrole.getUserId())) {
            json = json + "\"uid\":\"" + userrole.getUserId() + "\",";
        }

        json = json + "\"applicationId\":\"" + userrole.getApplicationId() + "\"," +
                "\"applicationName\":\"" + null + "\"," +
                "\"applicationRoleName\":\"" + userrole.getRoleName() + "\"," +
                "\"applicationRoleValue\":\"" + userrole.getRoleValue() + "\"," +
                "\"organizationName\":\"" + userrole.getOrgName() + "\"}";

        return json;

    }

    public static String getStringFromJsonpathExpression(String expression, String jsonString) throws PathNotFoundException {
        //String expression = "$.identity.uid";
        String value = "";
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        String result = JsonPath.read(document, expression);
        value = result.toString();

        return value;
    }

    private static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
