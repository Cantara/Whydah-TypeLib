package net.whydah.sso.ddd.model;

public class RoleId extends AbstractBaseId {

	public RoleId(String anId) {
		super(anId, 0, 36);
	}
	
	public static boolean isValid(String input) {
		try {
			new RoleId(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
