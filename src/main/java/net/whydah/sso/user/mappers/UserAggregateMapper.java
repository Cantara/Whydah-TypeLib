package net.whydah.sso.user.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.user.types.UserAggregate;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserAggregateMapper {

    public static final Logger log = LoggerFactory.getLogger(UserAggregateMapper.class);
    public static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public static UserAggregate fromJson(String userAggregateJson) {
        try {
            UserAggregate userAggregate = parseUserAggregateJson(userAggregateJson);
            if (userAggregate != null) {
                return userAggregate;
            }
        } catch (Exception e) {

        }
        try {
            UserAggregate userAggregate = parseUserIdentityJson(userAggregateJson);
            if (userAggregate != null) {
                return userAggregate;
            }
        } catch (Exception e) {

        }
        try {
            UserAggregate userAggregate = parseUserAggregateOldJson(userAggregateJson);
            if (userAggregate != null) {
                return userAggregate;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static UserAggregate fromUserIdentityJson(String userIdentityJson) {
        UserAggregate userAggregate = parseUserIdentityJson(userIdentityJson);
        return userAggregate;
    }

    public static UserAggregate fromUserAggregateNoIdentityJson(String userAggregateJson) {
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
            uid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..uid").toJSONString();
            if (uid.indexOf(",") > 0) {
                uid = uid.substring(2, uid.indexOf(",") - 1);
            } else {
                uid = uid.substring(2, uid.length() - 2);
            }
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
            email = email.substring(2, email.length() - 2);
            String cellPhone = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..cellPhone").toJSONString();
            cellPhone = cellPhone.substring(2, cellPhone.length() - 2);
            String personRef = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJSON, "$..personRef").toJSONString();
            personRef = personRef.substring(2, personRef.length() - 2);

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

    /**
     * {"uid":"useradmin","username":"useradmin","firstName":"UserAdmin","lastName":"UserAdminWebApp","personRef":"42","email":"whydahadmin@getwhydah.com","cellPhone":"87654321",
     * "roles": [{"applicationId":"19","applicationName":"","applicationRoleName":"WhydahUserAdmin","applicationRoleValue":"1","organizationName":""}]}
     */
    private static UserAggregate parseUserAggregateJson(String userAggregateJSON) {
        String uid = null;
        try {
            uid = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.uid");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have uid (yet)
        }
        try {
            String userName = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.username");
            String firstName = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.firstName");
            String lastName = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.lastName");
            String email = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.email");
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.cellPhone");
            String personRef = JsonPathHelper.getStringFromJsonpathExpression(userAggregateJSON, "$.identity.personRef");


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
            String uid = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.uid");
            String userName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.username");
            String firstName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.firstName");
            String lastName = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.lastName");
            String email = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.email");
            String cellPhone = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.cellPhone");
            String personRef = JsonPathHelper.getStringFromJsonpathExpression(userIdentityJSON, "$.identity.personRef");


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

    public static String toXML(UserAggregate userAggregate) {
        StringBuilder strb = new StringBuilder();
        String headAndIdentity = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<whydahuser>\n" +
                "    <identity>\n" +
                "        <username>" + userAggregate.getUsername() + "</username>\n" +
                "        <cellPhone>" + (userAggregate.getCellPhone() != null ? userAggregate.getCellPhone() : "") + "</cellPhone>\n" +
                "        <email>" + userAggregate.getEmail() + "</email>\n" +
                "        <firstname>" + userAggregate.getFirstName() + "</firstname>\n" +
                "        <lastname>" + userAggregate.getLastName() + "</lastname>\n" +
                "        <personRef>" + (userAggregate.getPersonRef() != null ? userAggregate.getPersonRef() : "") + "</personRef>\n" +
                "        <UID>" + userAggregate.getUid() + "</UID>\n" +
                "    </identity>\n" +
                "    <applications>\n";
        strb.append(headAndIdentity);

        for (UserApplicationRoleEntry u : userAggregate.getRoleList()) {
            strb.append(
                    "        <application>\n" +
                            "            <appId>" + u.getApplicationId() + "</appId>\n" +
                            "            <applicationName>" + u.getApplicationName() + "</applicationName>\n" +
                            "            <orgName>" + u.getOrgName() + "</orgName>\n" +
                            "            <roleName>" + u.getRoleName() + "</roleName>\n" +
                            "            <roleValue>" + u.getRoleValue() + "</roleValue>\n" +
                            "        </application>\n"
            );
        }
        strb.append(
                "    </applications>\n" +
                        "</whydahuser>"
        );
        return strb.toString();
    }

    public static List<UserAggregate> getFromJson(String jsonArray) throws JsonProcessingException, IOException {
        List<UserAggregate> list = new ArrayList<UserAggregate>();
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(jsonArray);

        if (!node.isArray() && node.has("result")) {
            node = node.get("result");
        }

        Iterator<JsonNode> iterator = node.elements();
        while (iterator.hasNext()) {


            JsonNode sNode = iterator.next();
            //TODO: check UserAggregateMapper.fromJson(...); there is a bug when reading uid (occurs when having more than one uid field in the json
            //UserAggregate ua = UserAggregateMapper.fromJson(sNode.toString());

            //Have to do manually for now
          
            String uid = sNode.get("uid")!=null? sNode.get("uid").textValue():null;
            String personRef = sNode.get("personRef")!=null? sNode.get("personRef").textValue():"";
            String username = sNode.get("username")!=null? sNode.get("username").textValue():null;
            String firstName = sNode.get("firstName")!=null? sNode.get("firstName").textValue():"";
            String lastName = sNode.get("lastName")!=null? sNode.get("lastName").textValue():"";
            String email = sNode.get("email")!=null? sNode.get("email").textValue():"";
            String cellPhone = sNode.get("cellPhone")!=null? sNode.get("cellPhone").textValue():"";


            UserAggregate ua = new UserAggregate(uid, username, firstName, lastName, personRef, email, cellPhone);

            if (sNode.has("roles")) {
                List<UserApplicationRoleEntry> roles = UserRoleMapper.fromJsonAsList(sNode.get("roles").toString());
                ua.setRoleList(roles);
            }
            list.add(ua);
        }
        return list;
    }


}
