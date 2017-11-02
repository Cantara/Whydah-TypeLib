package net.whydah.sso.user.types;

import net.whydah.sso.basehelpers.ValidationConfig;
import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.user.mappers.UserCredentialMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCredential {
    private String userName;
    private String password;
    private static final Logger log = LoggerFactory.getLogger(UserCredential.class);


    public UserCredential() {
    }

    public UserCredential(String userName, String password) {
        setUserName(userName);
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {

        if (userName == null || userName.length() > 90) {
            log.error("Attempt to create an illegal UserCredential - uid:{}", userName);
            this.userName = null;
        } else {
            this.userName = userName;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String toString() {
        return "UserCredential{" + "userName='" + userName + " ', password=******  }";
    }

    public String toXML() {
        return UserCredentialMapper.toXML(this);
    }

    public String toSafeXML() {
        return UserCredentialMapper.toSafeXML(this);
    }
    
    public boolean isValid(){
    	return Validator.isValidTextInput(userName, ValidationConfig.USERNAME_MIN_LENGTH, ValidationConfig.USERNAME_MAX_LENGTH) &&
    			Validator.isValidTextInput(password, ValidationConfig.PWD_MIN_LENGTH, ValidationConfig.PWD_MAX_LENGTH);
    }
}
