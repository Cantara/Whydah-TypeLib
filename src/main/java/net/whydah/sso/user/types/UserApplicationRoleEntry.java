package net.whydah.sso.user.types;

import net.whydah.sso.ddd.model.application.ApplicationId;
import net.whydah.sso.ddd.model.application.ApplicationName;
import net.whydah.sso.ddd.model.user.UID;
import net.whydah.sso.ddd.model.userrole.OrganizationName;
import net.whydah.sso.ddd.model.userrole.RoleId;
import net.whydah.sso.ddd.model.userrole.RoleName;
import net.whydah.sso.ddd.model.userrole.RoleValue;
import net.whydah.sso.user.helpers.UserRoleXpathHelper;
import net.whydah.sso.user.mappers.UserRoleMapper;

import java.io.Serializable;


public class UserApplicationRoleEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private ApplicationId applicationId; //required
	private ApplicationName applicationName=new ApplicationName("");
	private OrganizationName orgName=new OrganizationName("");
	private RoleName roleName; //required
	private RoleId id; //required
	private UID userId; //required
	private RoleValue roleValue; //required


	public void setApplicationId(String applicationId) {
		this.applicationId = new ApplicationId(applicationId);
	}

	public void setOrgName(String orgName) {
		this.orgName = new OrganizationName(orgName);
	}

	public void setRoleName(String roleName) {
		this.roleName = new RoleName(roleName);
	}

	public UserApplicationRoleEntry(){
		
	}
	
	public UserApplicationRoleEntry(String uid, String applicationId, String applicationName, String orgName, String roleName, String roleValue) {
		this(uid, applicationId, applicationName, orgName, null, roleName, roleValue);
	}
	

	public UserApplicationRoleEntry(String uid, String applicationId, String applicationName, String orgName, String roleId, String roleName, String roleValue) {

		if (uid != null && UID.isValid(uid)) {
			this.userId = new UID(uid);	
		}
		if(applicationId!=null){//only check when the value is set
			this.applicationId = new ApplicationId(applicationId);
		}
		if(applicationName!=null){//only check when the value is set
			this.applicationName = new ApplicationName(applicationName); 
		}
		if(orgName!=null){ //only check when the value is set
			this.orgName = new OrganizationName(orgName);
		}
		if(roleId!=null){ //only check when the value is set
			this.id = new RoleId(roleId);
		}
		if(roleName!=null){ ///only check when the value is set
			this.roleName = new RoleName(roleName);
		}
		if(roleValue!=null){ //only check when the value is set
			setRoleValue(roleValue);
		}
	}

	public static UserApplicationRoleEntry fromXml(String roleXml) {
		return UserRoleXpathHelper.fromXml(roleXml);
	}


	public String getRoleValue() {
		//return roleValue!=null? new String(Base64.getDecoder().decode(roleValue.getInput()), StandardCharsets.UTF_8):null;
		return roleValue!=null? roleValue.getInput() :null;
	}

	public void setRoleValue(String roleValue) {
		//this.roleValue = new RoleValue(Base64.getEncoder().encodeToString(roleValue.getBytes(StandardCharsets.UTF_8)));
		this.roleValue = new RoleValue(roleValue);
	}

	public String getRoleName() {
		return roleName!=null? roleName.getInput():null;
	}

	public String getOrgName() {
		return orgName!=null? orgName.getInput():null;
	}

	public String getApplicationId() {
		return applicationId!=null? applicationId.getId():null;
	}

	public String getId() {
		return id!=null?id.getId():null;
	}

	public void setId(String id) {
		this.id = new RoleId(id);
	}

	public String getUserId() {
		return userId!=null?userId.getId():null;
	}

	public void setUserId(String userId) {
		this.userId = new UID(userId);
	}

	public String getApplicationName() {
		return applicationName!=null?applicationName.getInput():null;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = new ApplicationName(applicationName);
	}

	public String toXML() {
		return UserRoleMapper.toXML(this);
	}

	public String toJson() {
		return UserRoleMapper.toJson(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserApplicationRoleEntry that = (UserApplicationRoleEntry) o;

		if (applicationId != null ? !applicationId.equals(that.applicationId) : that.applicationId != null)
			return false;
		if (applicationName != null ? !applicationName.equals(that.applicationName) : that.applicationName != null)
			return false;
		if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
		if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
		return roleValue != null ? roleValue.equals(that.roleValue) : that.roleValue == null;
	}

	@Override
	public int hashCode() {
		int result = applicationId != null ? applicationId.hashCode() : 0;
		result = 31 * result + (applicationName != null ? applicationName.hashCode() : 0);
		result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
		result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (userId != null ? userId.hashCode() : 0);
		result = 31 * result + (roleValue != null ? roleValue.hashCode() : 0);
		return result;
	}
}
