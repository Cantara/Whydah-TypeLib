package net.whydah.sso.user.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
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
        String userId = "";
        try {
            userId = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.uid");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have userId (yet)
        }
        String appId = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationId");
        String appName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationName");
        String orgName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.organizationName");
        String roleName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationRoleName");
        String roleValue = "";
        try {
            roleValue = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationRoleValue");
        } catch (Exception e) {
            // IT is OK for some IdentityStructures to not have userId (yet)
        }
        UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(null, appId, appName, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        return userRole;
    }

    public static List<UserApplicationRoleEntry> fromJsonAsList2(String roleJson) {
        List<UserApplicationRoleEntry> roles = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            roles = mapper.readValue(roleJson, new TypeReference<List<UserApplicationRoleEntry>>() {
            });

        } catch (JsonMappingException e) {
            throw new IllegalArgumentException("Error mapping json for " + roleJson, e);
        } catch (JsonParseException e) {
            throw new IllegalArgumentException("Error parsing json for " + roleJson, e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading json for " + roleJson, e);
        }
        return roles;
    }

    public static List<UserApplicationRoleEntry> fromJsonAsList(String roleJson) {

        List<UserApplicationRoleEntry> roleList = new LinkedList<UserApplicationRoleEntry>();
        try {
            JSONArray roles = (JSONArray) JSONValue.parseWithException(roleJson);
            if (roles != null) {
                for (int i = 0; i < roles.size(); i++) {
                    JSONObject roleentry = (JSONObject) roles.get(i);
                    UserApplicationRoleEntry role = new UserApplicationRoleEntry();
                    role.setId((String) roleentry.get("roleId"));
                    role.setApplicationId((String) roleentry.get("applicationId"));
                    role.setRoleName((String) roleentry.get("applicationName"));
                    role.setOrgName((String) roleentry.get("organizationName"));
                    role.setRoleName((String) roleentry.get("applicationRoleName"));
                    role.setRoleValue((String) roleentry.get("applicationRoleValue"));
                    role.setUserId((String) roleentry.get("uid"));
                    
                    roleList.add(role);
                }
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error mapping json for " + roleJson, e);

        }
        return roleList;
    }

    public static String toJson(UserApplicationRoleEntry userrole) {
    	
    	JSONObject jsonObj = new JSONObject();
    	
        if (isNotEmpty(userrole.getId())) {
        	jsonObj.put("roleId", userrole.getId());
        }
        if (isNotEmpty(userrole.getUserId())) {
        	jsonObj.put("uid", userrole.getId());
        }

        jsonObj.put("applicationId", userrole.getApplicationId());
        jsonObj.put("applicationName", userrole.getApplicationId());
        jsonObj.put("applicationRoleName", userrole.getApplicationName());
        jsonObj.put("applicationRoleValue", userrole.getRoleValue());
        jsonObj.put("organizationName", userrole.getOrgName());
        
        return jsonObj.toJSONString();

    	
//        String json = "{";
//        if (isNotEmpty(userrole.getId())) {
//            json = json + "\"roleId\":\"" + userrole.getId() + "\",";
//        }
//        if (isNotEmpty(userrole.getUserId())) {
//            json = json + "\"uid\":\"" + userrole.getUserId() + "\",";
//        }
//
//        json = json + "\"applicationId\":\"" + userrole.getApplicationId() + "\"," +
//                "\"applicationName\":\"" + userrole.getApplicationName() + "\"," +
//                "\"applicationRoleName\":\"" + userrole.getRoleName() + "\"," +
//                "\"applicationRoleValue\":\"" + userrole.getRoleValue() + "\"," +
//                "\"organizationName\":\"" + userrole.getOrgName() + "\"}";
//
//        return json;

    }

    //list of application data, no wrapping element "applications". Need to decide.
    public static String toJson(List<UserApplicationRoleEntry> roles) {
        String rolesJson = "[";
        for (UserApplicationRoleEntry role : roles) {
            rolesJson = rolesJson + toJson(role) + ",";
        }
        rolesJson = rolesJson.substring(0, rolesJson.length() - 1) + "]";
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
