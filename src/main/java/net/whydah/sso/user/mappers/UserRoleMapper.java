package net.whydah.sso.user.mappers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.PathNotFoundException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.LinkedList;
import java.util.List;

public class UserRoleMapper {

    public static final Logger log = LoggerFactory.getLogger(UserRoleMapper.class);
    public static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static UserApplicationRoleEntry fromXml(String roleXml) {

        String id = XpathHelper.findValue(roleXml, "/application/id");
        String userId = XpathHelper.findValue(roleXml, "/application/uid");
        String appId = XpathHelper.findValue(roleXml, "/application/appId");
        String appName = XpathHelper.findValue(roleXml, "/application/applicationName");
        String orgName = XpathHelper.findValue(roleXml, "/application/orgName");
        String roleName = XpathHelper.findValue(roleXml, "/application/roleName");
        String roleValue = XpathHelper.findValue(roleXml, "/application/roleValue");
        UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(null, appId, appName, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        return userRole;
    }

    public static UserApplicationRoleEntry fromJson(String roleJson) {

        String id = "";
        try {
            id = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.roleId");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have uid (yet)
        }
        try {
            id = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.id");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have uid (yet)
        }
        String userId = "";
        try {
            userId = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.uid");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have userId (yet)
        }
        try {
            userId = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.userId");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have userId (yet)
        }
        String appId = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationId");
        String appName = "";
        try {
            appName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationName");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have appName (yet)
        }
        String orgName = "";
        try {
            orgName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.organizationName");
        } catch (Exception e) {
            // IT is OK old format
        }
        try {
            orgName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.orgName");
        } catch (Exception e) {
            // IT is OK new format
        }
        String roleName = "";
        try {
            roleName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationRoleName");
        } catch (Exception e) {
            // IT is OK old format
        }
        try {
            roleName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.roleName");
        } catch (Exception e) {
            // IT is OK new format
        }
        String roleValue = "";
        try {
            roleValue = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationRoleValue");
        } catch (Exception e) {
            // IT is OK old format
        }
        try {
            roleValue = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.roleValue");
        } catch (Exception e) {
            // IT is OK new format
        }
        UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(userId, appId, appName, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        return userRole;
    }

//    public static List<UserApplicationRoleEntry> fromJsonAsList2(String roleJson) {
//        List<UserApplicationRoleEntry> roles = null;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            roles = mapper.readValue(roleJson, new TypeReference<List<UserApplicationRoleEntry>>() {
//            });
//
//        } catch (JsonMappingException e) {
//            throw new IllegalArgumentException("Error mapping json for " + roleJson, e);
//        } catch (JsonParseException e) {
//            throw new IllegalArgumentException("Error parsing json for " + roleJson, e);
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Error reading json for " + roleJson, e);
//        }
//        return roles;
//    }

    public static List<UserApplicationRoleEntry> fromJsonAsList(String roleJson) {

        List<UserApplicationRoleEntry> roleList = new LinkedList<UserApplicationRoleEntry>();
        try {
        	if(roleJson ==null){
        		return roleList;
        	}
            JSONArray roles = (JSONArray) JSONValue.parseWithException(roleJson);
            if (roles != null) {
                for (int i = 0; i < roles.size(); i++) {
                    JSONObject roleentry = (JSONObject) roles.get(i);
                    UserApplicationRoleEntry role = new UserApplicationRoleEntry();
                    if (roleentry.get("roleId") != null && roleentry.get("roleId").toString().length() > 5) {
                        role.setId((String) roleentry.get("roleId"));
                    } else {
                        role.setId((String) roleentry.get("id"));
                    }
                    role.setApplicationId((String) roleentry.get("applicationId"));
                    role.setApplicationName((String) roleentry.get("applicationName"));
                    if (roleentry.get("organizationName") != null && roleentry.get("organizationName").toString().length() > 5) {
                        role.setOrgName((String) roleentry.get("organizationName"));
                    } else {
                        role.setOrgName((String) roleentry.get("orgName"));
                    }
                    if (roleentry.get("applicationRoleName") != null && roleentry.get("applicationRoleName").toString().length() > 5) {
                        role.setRoleName((String) roleentry.get("applicationRoleName"));
                    } else {
                        role.setRoleName((String) roleentry.get("roleName"));
                    }
                    if (roleentry.get("applicationRoleValue") != null && roleentry.get("applicationRoleValue").toString().length() > 5) {
                        role.setRoleValue((String) roleentry.get("applicationRoleValue"));
                    } else {
                        role.setRoleValue((String) roleentry.get("roleValue"));
                    }
                    if (roleentry.get("uid") != null && roleentry.get("uid").toString().length() > 5) {
                        role.setUserId((String) roleentry.get("uid"));
                    } else {
                        role.setUserId((String) roleentry.get("userId"));
                    }

                    roleList.add(role);
                }
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error mapping json for " + roleJson, e);

        }
        return roleList;
    }

    public static String toJson(UserApplicationRoleEntry userrole) {

   /*     if (userrole==null){
            return "{}";
        }
    	try {
            return mapper.writeValueAsString(userrole);

        } catch (Exception e){
            return "[]";
        }
*/
        if (userrole == null) {
            return "{\"orgName\":\"\",\"roleValue\":\"\",\"roleName\":\"\",\"applicationId\":\"\",\"applicationName\":\"\"}";
        }

        JSONObject jsonObj = new JSONObject();

        if (isNotEmpty(userrole.getId())) {
            jsonObj.put("id", userrole.getId());
        }
        if (isNotEmpty(userrole.getUserId())) {
            jsonObj.put("userId", userrole.getUserId());
        }

        jsonObj.put("applicationId", userrole.getApplicationId() != null ? userrole.getApplicationId() : "");
        try {
            if (isNotEmpty(userrole.getApplicationName())) {
                jsonObj.put("applicationName", userrole.getApplicationName() != null ? userrole.getApplicationName() : "");
            }
        } catch (PathNotFoundException PNFF) {
            log.warn("PathNotFoundException {} - returning empty");
        }

        jsonObj.put("roleName", userrole.getRoleName() != null ? userrole.getRoleName() : "");
        jsonObj.put("roleValue", userrole.getRoleValue() != null ? userrole.getRoleValue() : "");
        jsonObj.put("orgName", userrole.getOrgName() != null ? userrole.getOrgName() : "");

        return jsonObj.toJSONString();


    }

    public static String toJsonOld(UserApplicationRoleEntry userrole) {

        JSONObject jsonObj = new JSONObject();

        if (isNotEmpty(userrole.getId())) {
            jsonObj.put("roleId", userrole.getId());
        }
        if (isNotEmpty(userrole.getUserId())) {
            jsonObj.put("uid", userrole.getUserId());
        }

        jsonObj.put("applicationId", userrole.getApplicationId() != null ? userrole.getApplicationId() : "");
        jsonObj.put("applicationName", userrole.getApplicationName() != null ? userrole.getApplicationName() : "");
        jsonObj.put("applicationRoleName", userrole.getRoleName() != null ? userrole.getRoleName() : "");
        jsonObj.put("applicationRoleValue", userrole.getRoleValue() != null ? userrole.getRoleValue() : "");
        jsonObj.put("organizationName", userrole.getOrgName() != null ? userrole.getOrgName() : "");

        return jsonObj.toJSONString();


    }

    //list of application data, no wrapping element "applications". Need to decide.
    public static String toJson(List<UserApplicationRoleEntry> roles) {
        String rolesJson = "[";
        for (UserApplicationRoleEntry role : roles) {
            rolesJson = rolesJson + toJson(role) + ",";
        }
        if (rolesJson.length() > 1) {
            rolesJson = rolesJson.substring(0, rolesJson.length() - 1) + "]";
        } else {
            rolesJson = rolesJson + "]";
        }
        return rolesJson;
    }


    public static String toXML(UserApplicationRoleEntry userrole) {
        return "<application>" +
                "            <roleId>" + userrole.getId() + "</roleId>\n" +
                "            <uid>" + userrole.getUserId() + "</uid>\n" +
                "            <username>" + userrole.getUserName() + "</username>\n" +
                "            <appId>" + userrole.getApplicationId() + "</appId>\n" +
                "            <applicationName>" + userrole.getApplicationName() + "</applicationName>\n" +
                "            <orgName>" + userrole.getOrgName() + "</orgName>\n" +
                "            <roleName>" + userrole.getRoleName() + "</roleName>\n" +
                "            <roleValue>" + userrole.getRoleValue() + "</roleValue>\n" +
                "</application>";
    }


    private static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
