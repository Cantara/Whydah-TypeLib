package net.whydah.sso.user.helpers;

import com.jayway.jsonpath.PathNotFoundException;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserRoleJsonPathHelper {

    private static final Logger log = LoggerFactory.getLogger(UserRoleXpathHelper.class);


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

}

