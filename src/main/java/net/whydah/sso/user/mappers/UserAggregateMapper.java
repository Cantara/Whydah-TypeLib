package net.whydah.sso.user.mappers;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.user.types.UserAggregate;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class UserAggregateMapper {

    public static final Logger log = LoggerFactory.getLogger(UserAggregateMapper.class);
    public static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    public static UserAggregate fromJson(String userAggregateJson) {
        UserAggregate userAggregate = parseUserAggregateJson(userAggregateJson);
        return userAggregate;
    }

    public static UserAggregate fromUserIdentityJson(String userIdentityJson) {
        UserAggregate userAggregate = parseUserIdentityJson(userIdentityJson);
        return userAggregate;
    }

    /**
     * {"uid":"useradmin","username":"useradmin","firstName":"UserAdmin","lastName":"UserAdminWebApp","personRef":"42","email":"whydahadmin@getwhydah.com","cellPhone":"87654321",
     * "roles": [{"applicationId":"19","applicationName":"","applicationRoleName":"WhydahUserAdmin","applicationRoleValue":"1","organizationName":""}]}
     */
    private static UserAggregate parseUserAggregateJson(String userAggregateJSON) {
        try {
            String uid = JsonPathHelper.getStringFromJsonpathExpression("$.identity.uid", userAggregateJSON);
            String userName = JsonPathHelper.getStringFromJsonpathExpression("$.identity.username", userAggregateJSON);
            String firstName = JsonPathHelper.getStringFromJsonpathExpression("$.identity.firstName", userAggregateJSON);
            String lastName = JsonPathHelper.getStringFromJsonpathExpression("$.identity.lastName", userAggregateJSON);
            String email = JsonPathHelper.getStringFromJsonpathExpression("$.identity.email", userAggregateJSON);
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression("$.identity.cellPhone", userAggregateJSON);
            String personRef = JsonPathHelper.getStringFromJsonpathExpression("$.identity.personRef", userAggregateJSON);


            UserAggregate userAggregate = new UserAggregate(uid, userName, firstName, lastName, personRef, email, cellPhone);
            List<UserApplicationRoleEntry> roleList = new ArrayList<>();

            JSONObject json = (JSONObject) JSONValue.parseWithException(userAggregateJSON);
            JSONArray roles = (JSONArray) json.get("roles");
            if (roles != null) {
                for (int i = 0; i < roles.size(); i++) {
                    JSONObject roleentry = (JSONObject) roles.get(i);
                    UserApplicationRoleEntry role = new UserApplicationRoleEntry();
                    role.setApplicationId((String) roleentry.get("applicationId"));
                    role.setRoleName((String) roleentry.get("applicationName"));
                    role.setOrgName((String) roleentry.get("organizationName"));
                    role.setRoleName((String) roleentry.get("applicationRoleName"));
                    role.setRoleValue((String) roleentry.get("applicationRoleValue"));
                    roleList.add(role);
                }
            }
            userAggregate.setRoleList(roleList);
            return userAggregate;
        } catch (Exception e) {
            log.error("Error parsing userAggregateJSON " + userAggregateJSON, e);
            return null;
        }
    }

    private static UserAggregate parseUserIdentityJson(String userIdentityJSON) {
        try {
            String uid = JsonPathHelper.getStringFromJsonpathExpression("$.identity.uid", userIdentityJSON);
            String userName = JsonPathHelper.getStringFromJsonpathExpression("$.identity.username", userIdentityJSON);
            String firstName = JsonPathHelper.getStringFromJsonpathExpression("$.identity.firstName", userIdentityJSON);
            String lastName = JsonPathHelper.getStringFromJsonpathExpression("$.identity.lastName", userIdentityJSON);
            String email = JsonPathHelper.getStringFromJsonpathExpression("$.identity.email", userIdentityJSON);
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression("$.identity.cellPhone", userIdentityJSON);
            String personRef = JsonPathHelper.getStringFromJsonpathExpression("$.identity.personRef", userIdentityJSON);


            UserAggregate userAggregate = new UserAggregate(uid, userName, firstName, lastName, personRef, email, cellPhone);
            return userAggregate;
        } catch (Exception e) {
            log.error("Error parsing userIdentityJSON " + userIdentityJSON, e);
            return null;
        }
    }

    public static String toJson(UserAggregate userAggregate) {
        StringBuilder strb = new StringBuilder();
        strb.append("{");
        String identity =
                "\"uid\":\"" + userAggregate.getUid() + "\"" +
                        ",\"username\":\"" + userAggregate.getUsername() + "\"" +
                        ",\"firstName\":\"" + userAggregate.getFirstName() + "\"" +
                        ",\"lastName\":\"" + userAggregate.getLastName() + "\"" +
                        ",\"personRef\":\"" + userAggregate.getPersonRef() +
                        "\",\"email\":\"" + userAggregate.getEmail() + "\"" +
                        ",\"cellPhone\":\"" + userAggregate.getCellPhone() + "\"";
        strb.append(identity);
        strb.append(",\"roles\": [");
        List<UserApplicationRoleEntry> roleList = userAggregate.getRoleList();
        for (UserApplicationRoleEntry role : roleList) {
            strb.append(role.toJson());
            strb.append(",");
        }
        if (roleList.size() > 0) {
            // remove last ","
            strb = strb.delete(strb.length() - 1, strb.length());
        }
        strb.append("]");
        strb.append("}");
        return strb.toString();
    }


}
