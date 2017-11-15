package net.whydah.sso.ddd.model;

public class Issuer extends AbstractUrl {
	
	public Issuer(String name) {
		super(name, true);
	}
	
	public static boolean isValid(String input) {
		try {
			new Issuer(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}

