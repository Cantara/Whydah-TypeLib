package net.whydah.sso.user.types;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.Password;
import net.whydah.sso.ddd.model.UserName;
import net.whydah.sso.user.mappers.UserCredentialMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCredential {
    private UserName userName;
    private Password password;
    private static final Logger log = LoggerFactory.getLogger(UserCredential.class);


    public UserCredential() {
    }

    public UserCredential(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public String getUserName() {
        return userName!=null?userName.getInput():null;
    }

    public void setUserName(String userName) {

        if (userName == null || userName.length() > 90) {
            log.error("Attempt to create an illegal UserCredential - uid:{}", userName);
            this.userName = null;
        } else {
            this.userName = new UserName(userName);
        }
    }

    public String getPassword() {
        return password!=null?password.getInput():null;
    }

    public void setPassword(String password) {
        this.password = new Password(password);
    }


    public String toString() {
        return "UserCredential{" + "userName='" + getUserName()+ " ', password=******  }";
    }

    public String toXML() {
        return UserCredentialMapper.toXML(this);
    }

    public String toSafeXML() {
        return UserCredentialMapper.toSafeXML(this);
    }
}
