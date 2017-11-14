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
			String appName;
			String rolename;
			String roleValue;
			List<String> roles = JsonPathHelper.findJsonpathList(userAggregateJson, "$.roles[*]");
			String uid = JsonPathHelper.findJsonPathNullableValue(userAggregateJson, "$.uid");
			UserApplicationRoleEntry[] result = new UserApplicationRoleEntry[roles.size()];
			for (int n = 0; n < roles.size(); n++) {

				appid = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationId");
				appName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationName");
				orgName = JsonPathHelper.findJsonPathNullableValue(userAggregateJson, "$.roles[" + n + "].organizationName");
				if(orgName==null){
					orgName = JsonPathHelper.findJsonPathNullableValue(userAggregateJson, "$.roles[" + n + "].orgName");
				}
				rolename = JsonPathHelper.findJsonPathNullableValue(userAggregateJson, "$.roles[" + n + "].applicationRoleName");
				if(rolename==null){
					rolename = JsonPathHelper.findJsonPathNullableValue(userAggregateJson, "$.roles[" + n + "].roleName");
				}
				roleValue = JsonPathHelper.findJsonPathNullableValue(userAggregateJson, "$.roles[" + n + "].applicationRoleValue");
				if(roleValue==null){
					roleValue = JsonPathHelper.findJsonPathNullableValue(userAggregateJson, "$.roles[" + n + "].roleValue");
				}
				UserApplicationRoleEntry ur = new UserApplicationRoleEntry(uid, appid, appName, orgName, rolename, roleValue);
				result[n] = ur;


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
				String roleid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..roleId").get(0).toString();
				String appid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationId").get(0).toString();
				String appName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationName").get(0).toString();
				String orgName = null;
				try{
					orgName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..organizationName").get(0).toString();
				}catch(Exception ex){               	
				}
				if(orgName==null){
					orgName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..orgName").get(0).toString();
				}    
				String roleName =null;
				try{
					roleName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationRoleName").get(0).toString();
				}catch(Exception ex){              	
				}
				if(roleName==null){
					roleName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..roleName").get(0).toString();
				}

				String roleValue =null;
				try{
					roleValue = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationRoleValue").get(0).toString();
				}catch(Exception ex){              	
				}
				if(roleValue==null){
					roleValue = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..roleValue").get(0).toString();
				}
				
				result = new UserApplicationRoleEntry(null, appid, appName, orgName, roleid, roleName, roleValue);
			} catch (PathNotFoundException pnpe) {
				return null;
			}

		}

		return result;
	}

}

