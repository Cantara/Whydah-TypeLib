package net.whydah.sso.ddd.sso;

import net.whydah.sso.ddd.WhydahIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.regex.Pattern;

public class NewPassword implements Serializable {

    private final String newPasswordToken;
    private final static Logger log = LoggerFactory.getLogger(WhydahIdentity.class);
    Pattern p = Pattern.compile("[^a-zA-Z0-9\\-]");

    public NewPassword(String inputNewpasswordToken) {

        // Must not be null
        if (inputNewpasswordToken == null) {
            log.error("Attempt to create an illegal NewPassword - value is null");
            this.newPasswordToken = null;
            // Must be a string, min length=3, max length 36
        } else if (inputNewpasswordToken.length() < 3 || inputNewpasswordToken.length() > 36) {
            log.error("Attempt to create an illegal NewPassword - illegal length:{}", inputNewpasswordToken.length());
            this.newPasswordToken = null;
            // Must be of only whitelisted characters
        } else if (p.matcher(inputNewpasswordToken).find()) {
            log.error("Attempt to create an illegal NewPassword - illegal characters - inputNewpasswordToken:{}", inputNewpasswordToken);
            this.newPasswordToken = null;
        } else {
            this.newPasswordToken = inputNewpasswordToken;
        }
    }


    public String getNewPasswordToken() {
        return newPasswordToken;
    }

    public boolean isValid() {
        return newPasswordToken != null;
    }

    public static boolean isValid(String passwordTokenToValidate) {
        try {
            return new NewPassword(passwordTokenToValidate).isValid();
        } catch (Exception e) {
        }
        return false;
    }


    @Override
    public String toString() {
        return newPasswordToken;
    }
}