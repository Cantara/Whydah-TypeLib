package net.whydah.sso.user.helpers;

import com.jayway.jsonpath.PathNotFoundException;

import net.whydah.sso.basehelpers.JsonPathHelper;
import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.user.types.UserApplicationRoleEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

public class UserRoleXpathHelper {

	private static final Logger log = LoggerFactory.getLogger(UserRoleXpathHelper.class);

	public static UserApplicationRoleEntry fromXml(String roleXml) {

		XpathHelper xpath;
		xpath = new XpathHelper(roleXml);
		if(!xpath.isValid()){
			return null;
		}
		String id =  xpath.findNullableValue("/application/id");
		String userId = xpath.findNullableValue("/application/uid");
		String appId = xpath.findNullableValue("/application/appId");
		String appName = xpath.findNullableValue("/application/applicationName");
		String orgName = xpath.findNullableValue("/application/orgName");
		if(orgName==null){
			orgName = xpath.findNullableValue("/application/organizationName");
		}
		String roleName = xpath.findNullableValue("/application/roleName");
		if(roleName==null){
			roleName = xpath.findNullableValue("/application/applicationRoleName");
		}		
		String roleValue = xpath.findNullableValue("/application/roleValue");
		if(roleName==null){
			roleName = xpath.findNullableValue("/application/applicationRoleValue");
		}
		UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(userId, appId, appName, orgName, id, roleName, roleValue);
		return userRole;


	}


	public static UserApplicationRoleEntry[] getUserRoleFromUserTokenXml(String userTokenXml) {

		String appid;
		String appName;
		String orgName;
		String rolename;
		String roleValue;



		XpathHelper xPath;
		xPath = new XpathHelper(userTokenXml);
		if(!xPath.isValid()){
			return null;
		}
		String uid = xPath.findNullableValue("/usertoken/uid");
		String expression = "count(/usertoken/application)";
		int noOfApps = Integer.valueOf(xPath.findNullableValue(expression));
		int noOfRoles = Integer.valueOf(xPath.findNullableValue("count(//role)"));
		UserApplicationRoleEntry[] result = new UserApplicationRoleEntry[noOfRoles];
		int roleindex = 0;
		for (int n = 1; n <= noOfApps; n++) {
			appid = xPath.findNullableValue("//usertoken/application[" + n + "]/@ID");
			int noSubRoles = Integer.valueOf(xPath.findNullableValue("count(//application[" + n + "]/organizationName)"));
			for (int m = 1; m <= noSubRoles; m++) {
				try {
					appName = xPath.findNullableValue("//application[" + n + "]/applicationName[" + m + "]");
					orgName = xPath.findNullableValue("//application[" + n + "]/orgName[" + m + "]");
					if(orgName==null){
						orgName = xPath.findNullableValue("//application[" + n + "]/organizationName[" + m + "]");
					}
					rolename = xPath.findNullableValue("//application[" + n + "]/role[" + m + "]/@name");
					roleValue = xPath.findNullableValue("//application[" + n + "]/role[" + m + "]/@value");
					UserApplicationRoleEntry ur = new UserApplicationRoleEntry(uid, appid, appName, orgName, rolename, roleValue);
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

		String appid;
		String orgName;
		String rolename;
		String roleValue;
		String appName;
		XpathHelper xPath;
		xPath = new XpathHelper(userAggregateXML);
		if(!xPath.isValid()){
			return null;
		}

		String uid = xPath.findNullableValue("/whydahuser/identity/uid");
		String expression = "count(/whydahuser/applications/application)";
		int noOfRoles = Integer.valueOf(xPath.findNullableValue(expression));

		for (int n = 1; n <= noOfRoles; n++) {
			try {
				   
				appid = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/appId");
				appName = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/applicationName");
				orgName = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/orgName");
				if(orgName==null){
					orgName = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/organizationName");
				}
				rolename = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/roleName");
				if(rolename==null){
					rolename = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/applicationRoleName");					
				}	
				roleValue = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/roleValue");
				if(roleValue==null){
					rolename = xPath.findNullableValue("/whydahuser/applications/application[" + n + "]/applicationRoleValue");
					
				}	
			
				UserApplicationRoleEntry userRole = new UserApplicationRoleEntry(uid, appid, appName, orgName, rolename, roleValue);
				userRoles.add(userRole);
			} catch (PathNotFoundException pnpe) {
				log.warn("Could not parse userAggregateXml {}, reason {}", userAggregateXML, pnpe.getMessage());
				//                    return null;
			}
		}


		return userRoles;
	}

//	public static UserApplicationRoleEntry[] getUserRoleFromUserAggregateJson(String userAggregateJson) {
//		if (userAggregateJson == null) {
//			log.debug("userAggregateJson was empty, so returning null.");
//		} else {
//			String appid;
//			String orgName = null;
//			String rolename;
//			String roleValue;
//			String appName;
//			List<String> roles = JsonPathHelper.findJsonpathList(userAggregateJson, "$.roles[*]");
//			String uid = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.uid");
//			UserApplicationRoleEntry[] result = new UserApplicationRoleEntry[roles.size()];
//			for (int n = 0; n < roles.size(); n++) {
//				
//					appid = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationId");
//					appName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationName");
//					try{
//						orgName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].orgName");
//					} catch (PathNotFoundException pnpe) {
//						
//					}
//					if(orgName==null){
//						try{
//							orgName = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].organizationName");
//						} catch (PathNotFoundException pnpe) {
//							return null;
//						}
//					}
//					rolename = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleName");
//					roleValue = JsonPathHelper.findJsonPathValue(userAggregateJson, "$.roles[" + n + "].applicationRoleValue");
//					UserApplicationRoleEntry ur = new UserApplicationRoleEntry(uid, appid, appName, orgName, rolename, roleValue);
//					result[n] = ur;
//				
//
//			}
//			return result;
//		}
//		return null;
//	}

//	public static UserApplicationRoleEntry getUserRoleFromJson(String userAggregateJson) {
//		UserApplicationRoleEntry result = null;
//		if (userAggregateJson == null) {
//			log.debug("userAggregateJson was empty, so returning null.");
//		} else {
//			try {
//				String roleid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..roleId").toString();
//				String appid = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationId").toJSONString();
//				String appName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationName").toJSONString();
//				String orgName = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..orgName").toJSONString();
//				String rolename = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationRoleName").toJSONString();
//				String roleValue = JsonPathHelper.getJsonArrayFromJsonpathExpression(userAggregateJson, "$..applicationRoleValue").toJSONString();
//				result = new UserApplicationRoleEntry(roleid, appid, appName, orgName, rolename, roleValue);
//			} catch (PathNotFoundException pnpe) {
//				return null;
//			}
//
//		}
//
//		return result;
//	}


	public static String getOrgNameFromRoleXml(String roleXml) {
		String orgName = "";
		if (roleXml == null) {
			log.debug("roleXml was empty, so returning empty orgName.");
		} else {
			return new XpathHelper(roleXml).findNullableValue("/application/orgName");
		}
		return orgName;
	}


}
