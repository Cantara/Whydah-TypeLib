package net.whydah.sso.user.mappers;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.user.types.UserAggregate;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import net.whydah.sso.user.types.UserIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserIdentityMapper {

    public static final Logger log = LoggerFactory.getLogger(UserIdentityMapper.class);


    public static UserIdentity fromJson(String someUserIdentityJSON) {
        try {
            UserIdentity userIdentity = parseUserIdentityJson(someUserIdentityJSON);
            if (userIdentity != null) {
                return userIdentity;
            }
        } catch (Exception e) {

        }
        try {
            UserIdentity userIdentity = parseUserAggregateJson(someUserIdentityJSON);
            if (userIdentity != null) {
                return userIdentity;
            }
        } catch (Exception e) {

        }
        try {
            UserIdentity userIdentity = parseUserAggregateOldJson(someUserIdentityJSON);
            if (userIdentity != null) {
                return userIdentity;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static UserIdentity fromUserIdentityJson(String userIdentityJSON) {
        UserIdentity userIdentity = parseUserIdentityJson(userIdentityJSON);
        return userIdentity;
    }


    public static UserIdentity fromUserAggregateJson(String userAggregateJson) {
        UserIdentity userIdentity = parseUserAggregateJson(userAggregateJson);
        return userIdentity;
    }

    public static UserAggregate fromUserIdentityWithNoIdentityJson(String userAggregateJson) {
        UserAggregate userAggregate = parseUserAggregateOldJson(userAggregateJson);
        return userAggregate;
    }

    /**
     * {"uid":"useradmin","username":"useradmin","firstName":"UserAdmin","lastName":"UserAdminWebApp","personRef":"42","email":"whydahadmin@getwhydah.com","cellPhone":"87654321",
     * "roles": [{"applicationId":"19","applicationName":"","applicationRoleName":"WhydahUserAdmin","applicationRoleValue":"1","organizationName":""}]}
     */
    private static UserAggregate parseUserAggregateOldJson(String userAggregateJSON) {
        String uid = null;
        try {
            uid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..uid").get(0).toString();
            // uid = uid.substring(2, uid.length() - 2);
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have uid (yet)
        }
        try {

            String userName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..username").toJSONString();
            userName = userName.substring(2, userName.length() - 2);
            String firstName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..firstName").toJSONString();
            firstName = firstName.substring(2, firstName.length() - 2);
            String lastName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..lastName").toJSONString();
            lastName = lastName.substring(2, lastName.length() - 2);
            String email = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..email").toJSONString();
            if(!email.contains("[null]") && !email.contains("[]")) {
            	email = email.substring(2, email.length() - 2);
            } else {
            	email = "";
            }
            String cellPhone = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..cellPhone").toJSONString();
            if(!cellPhone.contains("[null]") && !cellPhone.contains("[]")) {
            	cellPhone = cellPhone.substring(2, cellPhone.length() - 2);
            } else {
            	cellPhone = "";
            }
            String personRef = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..personRef").toJSONString();
            if(!personRef.contains("[null]") && !personRef.contains("[]")) {
            	personRef = personRef.substring(2, personRef.length() - 2);
            } else {
            	personRef = "";
            }


            UserAggregate userAggregate = new UserAggregate(uid, userName, firstName, lastName, personRef, email, cellPhone);

            JSONObject json = (JSONObject) JSONValue.parseWithException(userAggregateJSON);
            List<UserApplicationRoleEntry> roleList = UserRoleMapper.fromJsonAsList(json.getAsString("roles"));
            userAggregate.setRoleList(roleList);
            
            return userAggregate;
        } catch (Exception e) {
            log.error("Error parsing userAggregateJSON " + userAggregateJSON, e);
            return null;
        }
    }

    /**
     * {"uid":"useradmin","username":"useradmin","firstName":"UserAdmin","lastName":"UserAdminWebApp","personRef":"42","email":"whydahadmin@getwhydah.com","cellPhone":"87654321",
     * "roles": [{"applicationId":"19","applicationName":"","applicationRoleName":"WhydahUserAdmin","applicationRoleValue":"1","organizationName":""}]}
     */
    private static UserAggregate parseUserAggregateJson(String userAggregateJSON) {
        try {
            String uid = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.uid");
            String userName = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.username");
            String firstName = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.firstName");
            String lastName = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.lastName");
            String email = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.email");
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "userAggregateJSON,$.identity.cellPhone");
            String personRef = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.personRef");


            UserAggregate userAggregate = new UserAggregate(uid, userName, firstName, lastName, personRef, email, cellPhone);
            

            JSONObject json = (JSONObject) JSONValue.parseWithException(userAggregateJSON);
            List<UserApplicationRoleEntry> roleList = UserRoleMapper.fromJsonAsList(json.getAsString("roles"));
            userAggregate.setRoleList(roleList);
            return userAggregate;
        } catch (Exception e) {
            log.error("Error parsing userAggregateJSON " + userAggregateJSON, e);
            return null;
        }
    }

    public static UserIdentity parseUserIdentityJson(String userIdentityJSON) {
        try {
            String uid = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.uid");
            String userName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.username");
            String firstName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.firstName");
            String lastName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.lastName");
            String email = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.email");
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.cellPhone");
            String personRef = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.personRef");


            UserIdentity useridentity = new UserIdentity(uid, userName, firstName, lastName, personRef, email, cellPhone);

            return useridentity;
        } catch (Exception e) {
            log.error("Error parsing userIdentityJSON, using alternative structure ");
        }
        try {
            String uid = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.uid");
            String userName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.username");
            String firstName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.firstName");
            String lastName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.lastName");
            String email = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.email");
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.cellPhone");
            String personRef = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.personRef");


            UserIdentity useridentity = new UserIdentity(uid, userName, firstName, lastName, personRef, email, cellPhone);

            return useridentity;
        } catch (Exception e) {
            log.error("Error parsing userIdentityJSON " + userIdentityJSON, e);
        }
        try {
            String userName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.username");
            String firstName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.firstName");
            String lastName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.lastName");
            String email = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.email");
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.cellPhone");
            String personRef = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.personRef");


            UserIdentity useridentity = new UserIdentity(userName, firstName, lastName, personRef, email, cellPhone);

            return useridentity;
        } catch (Exception e) {
            log.error("Error parsing userIdentityJSON " + userIdentityJSON, e);
            return null;
        }
    }

    public static String toXML(UserIdentity userIdentity) {
        StringBuilder strb = new StringBuilder();
        String headAndIdentity = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<whydahuser>\n" +
                "    <identity>\n" +
                "        <username>" + userIdentity.getUsername() + "</username>\n" +
                "        <cellPhone>" + (userIdentity.getCellPhone() != null ? userIdentity.getCellPhone() : "") + "</cellPhone>\n" +
                "        <email>" + userIdentity.getEmail() + "</email>\n" +
                "        <firstname>" + userIdentity.getFirstName() + "</firstname>\n" +
                "        <lastname>" + userIdentity.getLastName() + "</lastname>\n" +
                "        <personRef>" + (userIdentity.getPersonRef() != null ? userIdentity.getPersonRef() : "") + "</personRef>\n" +
                "    </identity>\n";
        strb.append(headAndIdentity);

        strb.append(
                "</whydahuser>"
        );
        return strb.toString();
    }

    public static String toJson(UserIdentity userIdentity) {
        if (userIdentity == null) {
            return "{}";
        }
        String userJson = "{\"uid\":\"" + userIdentity.getUid() + "\",\"username\":\"" + userIdentity.getUsername() + "\",\"firstName\":\"" + userIdentity.getFirstName() + "\",\"lastName\":\"" + userIdentity.getLastName() + "\",\"personRef\":\"" + userIdentity.getPersonRef() +
                "\",\"email\":\"" + userIdentity.getEmail() + "\",\"cellPhone\":\"" + userIdentity.getCellPhone() + "\"}";
        return userJson;
    }

    public static String toJsonWithoutUID(UserIdentity userIdentity) {
        String userJson = "{\"username\":\"" + userIdentity.getUsername() + "\",\"firstName\":\"" + userIdentity.getFirstName() + "\",\"lastName\":\"" + userIdentity.getLastName() + "\",\"personRef\":\"" + userIdentity.getPersonRef() +
                "\",\"email\":\"" + userIdentity.getEmail() + "\",\"cellPhone\":\"" + userIdentity.getCellPhone() + "\"}";
        return userJson;
    }

}
