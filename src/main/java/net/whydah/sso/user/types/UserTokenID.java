package net.whydah.sso.user.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class UserTokenID implements Serializable {

    private final String id;
    private final static Logger log = LoggerFactory.getLogger(UserTokenID.class);

    public UserTokenID(String userTokenId) {

        if (userTokenId == null || userTokenId.length() > 36) {
            log.error("Attempt to create an illegal UserTokenID - userTokenId:{}", userTokenId);
            this.id = null;
        } else {
            this.id = userTokenId;
        }
    }

    public String getUsertokenId() {
        return id;
    }

    public String getId() {
        return id;
    }

    public boolean isValid() {
        return id != null;
    }

    public static UserTokenID fromUserTokenID(String usertokenid) {
        return new UserTokenID(usertokenid);
    }

    public static UserTokenID invalidTokenID() {
        return new UserTokenID(null);
    }

    @Override
    public String toString() {
        return id;
    }


}
