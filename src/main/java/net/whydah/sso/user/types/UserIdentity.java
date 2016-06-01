package net.whydah.sso.user.types;

import java.io.Serializable;


public class UserIdentity implements Serializable {
    private static final long serialVersionUID = 20;
    protected String uid;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String personRef;
    protected String email;
    protected String cellPhone;

    public UserIdentity() {

    }

    public UserIdentity(String uid, String username, String firstName, String lastName, String personRef, String email, String cellPhone) {
        this.uid = uid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personRef = personRef;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public UserIdentity(String username, String firstName, String lastName, String personRef, String email, String cellPhone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personRef = personRef;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public String getPersonName() {
        return firstName + ' ' + lastName;
    }

    public String getPersonRef() {
        return personRef!=null?personRef:"";
    }

    public void setPersonRef(String personRef) {
        this.personRef = personRef;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        if (cellPhone == null) {
            return "";
        }
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }



}
