package net.whydah.sso.ddd.model;


import net.whydah.sso.ddd.model.base.AbstractBaseId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTokenId extends AbstractBaseId {

    private static final Logger log = LoggerFactory.getLogger(AbstractBaseId.class);


    public UserTokenId(String id) {
        super(id, 20, 36);
	}

    public static boolean isValid(String id) {
		try {
			new UserTokenId(id);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}