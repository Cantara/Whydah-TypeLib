package net.whydah.sso.ddd.model;

public class LastName extends AbstractName {
	
	public LastName(String name) {
		super(name);
	}
	
	public static boolean isValid(String input) {
		try {
			new LastName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
