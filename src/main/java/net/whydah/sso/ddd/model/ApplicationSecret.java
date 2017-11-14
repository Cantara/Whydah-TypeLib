package net.whydah.sso.ddd.model;

public class ApplicationSecret extends AbstractName {
	
	public ApplicationSecret(String secret) {
		super(secret);
	}

	public static boolean isValid(String secret) {
		try {
			new ApplicationSecret(secret);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
