package net.whydah.sso.user.types;

import net.whydah.sso.user.helpers.UserRoleXpathHelper;
import net.whydah.sso.user.mappers.UserRoleMapper;

import java.io.Serializable;


public class UserApplicationRoleEntry implements Serializable {

    private String userName="";
    private String applicationId="";


    private String applicationName="";
    private String orgName="";
    private String roleName="";
    private String id = null;
    private String userId = null;
    private String roleValue="";

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public UserApplicationRoleEntry() {

    }

    public UserApplicationRoleEntry(String userName, String applicationId, String orgName, String roleName) {
        this.userName = userName;
        this.applicationId = applicationId;
        this.orgName = orgName;
        this.roleName = roleName;
    }

    public UserApplicationRoleEntry(String userName, String applicationId, String orgName, String roleName, String roleValue) {
        this.userName = userName;
        this.applicationId = applicationId;
        this.orgName = orgName;
        this.roleName = roleName;
        this.roleValue = roleValue;
    }

    public UserApplicationRoleEntry(String userName, String applicationId, String applicationName, String orgName, String roleName, String roleValue) {
        this.userName = userName;
        this.applicationId = applicationId;
        this.orgName = orgName;
        this.roleName = roleName;
        this.roleValue = roleValue;
        this.applicationName = applicationName;
    }

    public static UserApplicationRoleEntry fromXml(String roleXml) {
        return UserRoleXpathHelper.fromXml(roleXml);
    }

    public String getUserName() {
        return userName;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String toXML() {
        return UserRoleMapper.toXML(this);
    }

    public String toJson() {
        return UserRoleMapper.toJson(this);
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
