package net.whydah.sso.ddd.model;

public class FirstName extends AbstractName {
	
	public FirstName(String name) {
		super(name);
	}
	
	public static boolean isValid(String input) {
		try {
			new FirstName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}

