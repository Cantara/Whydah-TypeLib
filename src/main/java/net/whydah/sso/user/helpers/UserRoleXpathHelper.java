package net.whydah.sso.user.helpers;

import com.jayway.jsonpath.PathNotFoundException;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserRoleXpathHelper {

    private static final Logger log = LoggerFactory.getLogger(UserRoleXpathHelper.class);

    public static UserApplicationRoleEntry fromXml(String roleXml) {

    	XpathHelper xpath = new XpathHelper(roleXml);
        String id =  xpath.findValue("/application/id");
        String userId = xpath.findValue("/application/uid");
        String appId = xpath.findValue("/application/appId");
        String appName = xpath.findValue("/application/applicationName");
        String orgName = xpath.findValue("/application/orgName");
        String roleName = xpath.findValue("/application/roleName");
        String roleValue = xpath.findValue("/application/roleValue");
        UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(null, appId, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        userRole.setApplicationName(appName);
        return userRole;
    }


    public static UserApplicationRoleEntry[] getUserRoleFromUserTokenXml(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty userTokenId.");
            return null;
        }
        if (!net.whydah.sso.user.mappers.UserTokenMapper.isSane(userTokenXml)) {
            log.warn(" XML injection detected - called with userTokenXml:{} - Returning null", userTokenXml);
            return null;
        }

        String appid;
            String orgName;
            String rolename;
            String roleValue;
            
            XpathHelper xPath = new XpathHelper(userTokenXml);
            String userName = xPath.findValue("/usertoken/username");
            String expression = "count(/usertoken/application)";
            int noOfApps = Integer.valueOf(xPath.findValue(expression));
            int noOfRoles = Integer.valueOf(xPath.findValue("count(//role)"));
            UserApplicationRoleEntry[] result = new UserApplicationRoleEntry[noOfRoles];
            int roleindex = 0;
            for (int n = 1; n <= noOfApps; n++) {
                appid = xPath.findValue("//usertoken/application[" + n + "]/@ID");
                int noSubRoles = Integer.valueOf(xPath.findValue("count(//application[" + n + "]/organizationName)"));
                for (int m = 1; m <= noSubRoles; m++) {
                    try {
                        orgName = xPath.findValue("//application[" + n + "]/organizationName[" + m + "]");
                        rolename = xPath.findValue("//application[" + n + "]/role[" + m + "]/@name");
                        roleValue = xPath.findValue("//application[" + n + "]/role[" + m + "]/@value");
                        UserApplicationRoleEntry ur = new UserApplicationRoleEntry(userName, appid, orgName, rolename, roleValue);
                        result[roleindex++] = ur;
                    } catch (PathNotFoundException pnpe) {
                        return null;
                    }

                }
            }
            return result;
    }

    public static List<UserApplicationRoleEntry> getUserRoleFromUserAggregateXml(String userAggregateXML) {
        List<UserApplicationRoleEntry> userRoles = new ArrayList<>();
        if (userAggregateXML == null) {
            log.debug("userAggregateXML was empty, so returning null.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            XpathHelper xPath = new XpathHelper(userAggregateXML);
            String userName = xPath.findValue("/whydahuser/identity/username");
            String expression = "count(/whydahuser/applications/application)";
            int noOfRoles = Integer.valueOf(xPath.findValue(expression));

            for (int n = 1; n <= noOfRoles; n++) {
                try {
                    appid = xPath.findValue("/whydahuser/applications/application[" + n + "]/appId");
                    orgName = xPath.findValue("/whydahuser/applications/application[" + n + "]/orgName");
                    rolename = xPath.findValue("/whydahuser/applications/application[" + n + "]/roleName");
                    roleValue = xPath.findValue("/whydahuser/applications/application[" + n + "]/roleValue");
                    UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(userName, appid, orgName, rolename, roleValue);
                    userRoles.add(userRole);
                } catch (PathNotFoundException pnpe) {
                    log.warn("Could not parse userAggregateXml {}, reason {}", userAggregateXML, pnpe.getMessage());
//                    return null;
                }
            }

        }
        return userRoles;
    }

    public static UserApplicationRoleEntry[] getUserRoleFromUserAggregateJson(String userAggregateJson) {
        if (userAggregateJson == null) {
            log.debug("userAggregateJson was empty, so returning null.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            List<String> roles = JsonPathHelper.findJsonpathList(userAggregateJson, "$.roles[*]");
            String userName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.username");
            UserApplicationRoleEntry[] result = new UserApplicationRoleEntry[roles.size()];
            for (int n = 0; n < roles.size(); n++) {
                try {
                    appid = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationId");
                    orgName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationName");
                    rolename = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleName");
                    roleValue = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleValue");
                    UserApplicationRoleEntry ur = new UserApplicationRoleEntry(userName, appid, orgName, rolename, roleValue);
                    result[n] = ur;
                } catch (PathNotFoundException pnpe) {
                    return null;
                }

            }
            return result;
        }
        return null;
    }

    public static UserApplicationRoleEntry getUserRoleFromJson(String userAggregateJson) {
        UserApplicationRoleEntry result = null;
        if (userAggregateJson == null) {
            log.debug("userAggregateJson was empty, so returning null.");
        } else {
            try {
                String roleid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..roleId").toJSONString();
                String appid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationId").toJSONString();
                String orgName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationName").toJSONString();
                String rolename = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationRoleName").toJSONString();
                String roleValue = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationRoleValue").toJSONString();
                result = new UserApplicationRoleEntry(roleid, appid, orgName, rolename, roleValue);
            } catch (PathNotFoundException pnpe) {
                return null;
            }

        }

        return result;
    }


    public static String getOrgNameFromRoleXml(String roleXml) {
        String orgName = "";
        if (roleXml == null) {
            log.debug("roleXml was empty, so returning empty orgName.");
        } else {
            String expression = "/application/orgName";
            orgName = new XpathHelper(roleXml).findValue(expression);
        }
        return orgName;
    }


}
