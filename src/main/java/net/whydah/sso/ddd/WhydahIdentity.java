package net.whydah.sso.ddd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.regex.Pattern;

public class WhydahIdentity implements Serializable {

    private final String id;
    private final static Logger log = LoggerFactory.getLogger(WhydahIdentity.class);
    Pattern p = Pattern.compile("[^a-zA-Z0-9\\-]");

    public WhydahIdentity(String whydahId) {

        // Must not be null
        if (whydahId == null) {
            log.error("Attempt to create an illegal WhydahIdentity - value is null");
            this.id = null;
            // Must be a string, min length=3, max length 36
        } else if (whydahId.length() < 4 || whydahId.length() > 36) {
            log.error("Attempt to create an illegal WhydahIdentity - illegal length:{}", whydahId.length());
            this.id = null;
            // Must be of only whitelisted characters
        } else if (p.matcher(whydahId).find()) {
            log.error("Attempt to create an illegal WhydahIdentity - illegal characters - whydahId:{}", whydahId);
            this.id = null;
        } else {
            this.id = whydahId;
        }
    }


    public String getId() {
        return id;
    }

    public boolean isValid() {
        return id != null;
    }


    @Override
    public String toString() {
        return id;
    }
}