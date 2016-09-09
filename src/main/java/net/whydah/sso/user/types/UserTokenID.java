package net.whydah.sso.user.types;

import java.io.Serializable;

public class UserTokenID implements Serializable {

    private final String id;

    public UserTokenID(String id) {
        this.id = id;
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
