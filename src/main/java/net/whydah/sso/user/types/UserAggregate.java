package net.whydah.sso.user.types;


import java.util.List;

public class UserAggregate extends UserIdentity {

    public UserAggregate() {

    }

    public UserAggregate(String uid, String username, String firstName, String lastName, String personRef, String email, String cellPhone) {
        this.uid = uid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personRef = personRef;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public UserAggregate(String username, String firstName, String lastName, String personRef, String email, String cellPhone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personRef = personRef;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public List<UserApplicationRoleEntry> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserApplicationRoleEntry> roleList) {
        this.roleList = roleList;
    }

    private List<UserApplicationRoleEntry> roleList;

}
