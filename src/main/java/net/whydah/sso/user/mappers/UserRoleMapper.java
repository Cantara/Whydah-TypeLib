package net.whydah.sso.user.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.List;

public class UserRoleMapper {

    public static final Logger log = LoggerFactory.getLogger(UserRoleMapper.class);
    public static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


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
        String userId = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.uid");
        String appId = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationId");
        String appName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationName");
        String orgName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.organizationName");
        String roleName = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationRoleName");
        String roleValue = JsonPathHelper.getStringFromJsonpathExpression(roleJson, "$.applicationRoleValue");
        UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(null, appId, appName, orgName, roleName, roleValue);
        userRole.setId(id);
        userRole.setUserId(userId);
        return userRole;
    }

    public static List<UserApplicationRoleEntry> fromJsonAsList(String roleJson) {
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


    public static String toJson(UserApplicationRoleEntry userrole) {
        String json = "{";
        if (isNotEmpty(userrole.getId())) {
            json = json + "\"roleId\":\"" + userrole.getId() + "\",";
        }
        if (isNotEmpty(userrole.getUserId())) {
            json = json + "\"uid\":\"" + userrole.getUserId() + "\",";
        }

        json = json + "\"applicationId\":\"" + userrole.getApplicationId() + "\"," +
                "\"applicationName\":\"" + userrole.getApplicationName() + "\"," +
                "\"applicationRoleName\":\"" + userrole.getRoleName() + "\"," +
                "\"applicationRoleValue\":\"" + userrole.getRoleValue() + "\"," +
                "\"organizationName\":\"" + userrole.getOrgName() + "\"}";

        return json;

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
