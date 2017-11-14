package net.whydah.sso.user.types;

import java.io.Serializable;

import net.whydah.sso.ddd.model.CellPhone;
import net.whydah.sso.ddd.model.Email;
import net.whydah.sso.ddd.model.FirstName;
import net.whydah.sso.ddd.model.LastName;
import net.whydah.sso.ddd.model.PersonRef;
import net.whydah.sso.ddd.model.UID;
import net.whydah.sso.ddd.model.UserName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserIdentity implements Serializable {
    private static final long serialVersionUID = 20;
    protected UID uid;
    protected UserName username;
    protected FirstName firstName;
    protected LastName lastName;
    protected PersonRef personRef;
    protected Email email;
    protected CellPhone cellPhone;
    private static final Logger log = LoggerFactory.getLogger(UserIdentity.class);


    public UserIdentity() {

    }


    public UserIdentity(String uid) {
        this.uid = new UID(uid);
    }

    public UserIdentity(String uid, String username, String firstName, String lastName, String personRef, String email, String cellPhone) {
        setUid(uid);
        this.username = new UserName(username);
        this.firstName = new FirstName(firstName);
        this.lastName = new LastName(lastName);
        this.personRef = new PersonRef(personRef);
        this.email = new Email(email);
        this.cellPhone = new CellPhone(cellPhone);
    }

    public UserIdentity(String username, String firstName, String lastName, String personRef, String email, String cellPhone) {
    	this.setUid(username); //as default
        this.username = new UserName(username);
        this.firstName = new FirstName(firstName);
        this.lastName = new LastName(lastName);
        this.personRef = new PersonRef(personRef);
        this.email = new Email(email);
        this.cellPhone = new CellPhone(cellPhone);
    }

    public String getPersonName() {
        return (firstName!=null?firstName.getInput():"") + ' ' + (lastName!=null?lastName.getInput():"");
    }

    public String getPersonRef() {
        return personRef!=null?personRef.getInput():"";
    }

    public void setPersonRef(String personRef) {
        this.personRef = new PersonRef(personRef);
    }

    public String getUid() {
        return uid!=null?uid.getId():null;
    }

    public void setUid(String uid) {

        if (uid == null || uid.length() > 36) {
            log.error("Attempt to create an illegal UserIdentity - uid:{}", uid);
            this.uid = null;
        } else {
            this.uid = new UID(uid.trim());
        }
    }

    public String getUsername() {
        return username!=null?username.getInput():null;
    }

    public void setUsername(String username) {
        this.username = new UserName(username.trim());
    }

    public String getFirstName() {
        return firstName!=null?firstName.getInput():null;
    }

    public void setFirstName(String firstName) {
        this.firstName = new FirstName(firstName);
    }

    public String getLastName() {
        return lastName!=null?lastName.getInput():null;
    }

    public void setLastName(String lastName) {
        this.lastName = new LastName(lastName);
    }

    public String getEmail() {
        return email!=null?email.getInput():null;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public String getCellPhone() {
        return cellPhone!=null?cellPhone.getInput():null;
    }

    public void setCellPhone(String cellPhone) {
        if (cellPhone != null && cellPhone.isEmpty()) {
            this.cellPhone = null;
            return;
        }
        this.cellPhone = new CellPhone(cellPhone);
    }


}
