package net.whydah.sso.user.helpers;

import com.jayway.jsonpath.PathNotFoundException;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.user.types.UserRoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserRoleXpathHelper {

    private static final Logger log = LoggerFactory.getLogger(UserRoleXpathHelper.class);

    public static UserRoleVO fromXml(String roleXml) {

        String id = XpathHelper.findValue(roleXml, "/application/id");
        String userId = XpathHelper.findValue(roleXml, "/application/uid");
        String appId = XpathHelper.findValue(roleXml, "/application/appId");
        String appName = XpathHelper.findValue(roleXml, "/application/applicationName");
        String orgName = XpathHelper.findValue(roleXml, "/application/orgName");
        String roleName = XpathHelper.findValue(roleXml, "/application/roleName");
        String roleValue = XpathHelper.findValue(roleXml, "/application/roleValue");
        UserRoleVO userRole = new UserRoleVO(null, appId, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        return userRole;
    }


    public static UserRoleVO[] getUserRoleFromUserTokenXml(String userTokenXml) {
        if (userTokenXml == null) {
            log.debug("userTokenXml was empty, so returning empty userTokenId.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            String userName = XpathHelper.findValue(userTokenXml, "/usertoken/username");
            String expression = "count(/usertoken/application)";
            int noOfApps = Integer.valueOf(XpathHelper.findValue(userTokenXml, expression));
            int noOfRoles = Integer.valueOf(XpathHelper.findValue(userTokenXml, "count(//role)"));
            UserRoleVO[] result = new UserRoleVO[noOfRoles];
            int roleindex=0;
            for (int n=1;n<=noOfApps;n++) {
                appid = XpathHelper.findValue(userTokenXml, "//usertoken/application[" + n + "]/@ID");
                int noSubRoles = Integer.valueOf(XpathHelper.findValue(userTokenXml, "count(//application[" + n + "]/organizationName)"));
                    for (int m=1;m<=noSubRoles;m++){
                        try {
                            orgName = XpathHelper.findValue(userTokenXml, "//application[" + n + "]/organizationName[" + m + "]");
                            rolename = XpathHelper.findValue(userTokenXml, "//application[" + n + "]/role[" + m + "]/@name");
                            roleValue = XpathHelper.findValue(userTokenXml, "//application[" + n + "]/role[" + m + "]/@value");
                        UserRoleVO ur = new UserRoleVO(userName, appid, orgName, rolename, roleValue);
                        result[roleindex++] = ur;
                        } catch (PathNotFoundException pnpe) {
                            return null;
                        }

                    }
            }
            return result;
        }
        return null;
    }

    public static List<UserRoleVO> getUserRoleFromUserAggregateXml(String userAggregateXML) {
        List<UserRoleVO> userRoles = new ArrayList<>();
        if (userAggregateXML == null) {
            log.debug("userAggregateXML was empty, so returning null.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            String userName = XpathHelper.findValue(userAggregateXML, "/whydahuser/identity/username");
            String expression = "count(/whydahuser/applications/application)";
            int noOfRoles = Integer.valueOf(XpathHelper.findValue(userAggregateXML, expression));

            for (int n=1;n<=noOfRoles;n++) {
                try {
                    appid = XpathHelper.findValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/appId");
                    orgName = XpathHelper.findValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/orgName");
                    rolename = XpathHelper.findValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/roleName");
                    roleValue = XpathHelper.findValue(userAggregateXML, "/whydahuser/applications/application[" + n + "]/roleValue");
                    UserRoleVO userRole = new UserRoleVO(userName, appid, orgName, rolename, roleValue);
                    userRoles.add(userRole);
                } catch (PathNotFoundException pnpe) {
                    log.warn("Could not parse userAggregateXml {}, reason {}", userAggregateXML,pnpe.getMessage());
//                    return null;
                }
            }

        }
        return userRoles;
    }

    public static UserRoleVO[] getUserRoleFromUserAggregateJson(String userAggregateJson) {
        if (userAggregateJson == null) {
            log.debug("userAggregateJson was empty, so returning null.");
        } else {
            String appid;
            String orgName;
            String rolename;
            String roleValue;
            List<String> roles = JsonPathHelper.findJsonpathList(userAggregateJson, "$.roles[*]");
            String userName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.username");
            UserRoleVO[] result = new UserRoleVO[roles.size()];
            for (int n=0;n<roles.size();n++){
                try {
                    appid = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationId");
                    orgName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationName");
                    rolename = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleName");
                    roleValue = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleValue");
                    UserRoleVO ur = new UserRoleVO(userName,appid, orgName, rolename, roleValue);
                    result[n]=ur;
                } catch (PathNotFoundException pnpe){
                    return null;
                }

            }
            return result;
        }
        return null;
    }



    public static String getOrgNameFromRoleXml(String roleXml) {
        String orgName = "";
        if (roleXml == null) {
            log.debug("roleXml was empty, so returning empty orgName.");
        } else {
            String expression = "/application/orgName";
            orgName = XpathHelper.findValue(roleXml, expression);
        }
        return orgName;
    }





}