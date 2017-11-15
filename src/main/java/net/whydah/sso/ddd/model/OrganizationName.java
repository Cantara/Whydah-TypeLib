package net.whydah.sso.ddd.model;

public class OrganizationName extends AbstractName {

	public OrganizationName(String input) {
		super(input);
	}
	
	public static boolean isValid(String input) {
		try {
			new OrganizationName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
