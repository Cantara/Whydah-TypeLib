package net.whydah.sso.user.types;

import net.whydah.sso.user.mappers.UserRoleMapper;
import net.whydah.sso.user.helpers.UserRoleXpathHelper;


public class UserRoleVO {

    private  String userName;
    private  String applicationId;
    private  String orgName;
    private  String roleName;
    private String id = null;
    private String userId = null;
    private String roleValue;

    public UserRoleVO(){

    }

    public UserRoleVO(String userName, String applicationId, String orgName, String roleName) {
        this.userName = userName;
        this.applicationId = applicationId;
        this.orgName = orgName;
        this.roleName = roleName;
    }

    public UserRoleVO(String userName, String applicationId, String orgName, String roleName, String roleValue) {
        this.userName = userName;
        this.applicationId = applicationId;
        this.orgName = orgName;
        this.roleName = roleName;
        this.roleValue = roleValue;
    }

    public static UserRoleVO fromXml(String roleXml) {
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

    public String toXML() {
        return "<application>" +
                "            <uid>" + getUserName() + "</uid>\n" +
                "            <appId>" + getApplicationId() + "</appId>\n" +
//                "            <applicationName>" + getApplicationName() + "</applicationName>\n" +
                "            <orgName>" + getOrgName() + "</orgName>\n" +
                "            <roleName>" + getRoleName() + "</roleName>\n" +
                "            <roleValue>" + getRoleValue() + "</roleValue>\n" +
                "</application>";
    }

    public String toJson(){
        return UserRoleMapper.toJson(this);
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
