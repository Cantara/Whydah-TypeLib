package net.whydah.sso.user.types;

public class UserTokenID {

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

    public static UserTokenID fromUserTokenId(String usertokenid) {
        return new UserTokenID(usertokenid);
    }

    public static UserTokenID invalidTokenId() {
        return new UserTokenID(null);
    }

    @Override
    public String toString() {
        return id;
    }


}
