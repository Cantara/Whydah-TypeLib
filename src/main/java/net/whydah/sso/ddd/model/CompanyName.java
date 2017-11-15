package net.whydah.sso.ddd.model;

public class CompanyName extends AbstractName {
	
	public CompanyName(String name) {
		super(name);
	}
	
	public static boolean isValid(String input) {
		try {
			new CompanyName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
