package net.whydah.sso.ddd.model;

public class ApplicationUrl extends AbstractUrl {

	public ApplicationUrl(String input) {
		super(input);
	}

	public static boolean isValid(String input) {
		try {
			new ApplicationUrl(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}


}
