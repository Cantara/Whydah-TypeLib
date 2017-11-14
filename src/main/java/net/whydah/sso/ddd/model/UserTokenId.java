package net.whydah.sso.ddd.model;


public class UserTokenId extends AbstractBaseId {

    public UserTokenId(String id) {
		super(id);
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