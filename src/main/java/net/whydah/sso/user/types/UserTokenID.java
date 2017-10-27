package net.whydah.sso.user.types;

import net.whydah.sso.ddd.WhydahIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserTokenID extends WhydahIdentity {

    private final static Logger log = LoggerFactory.getLogger(UserTokenID.class);

    public UserTokenID(String userTokenId) {
        super(userTokenId);
    }


    public static UserTokenID fromUserTokenID(String usertokenid) {
        return new UserTokenID(usertokenid);
    }

    public static UserTokenID invalidTokenID() {
        return new UserTokenID(null);
    }

    @Override
    public String toString() {
        return getId();
    }


}
