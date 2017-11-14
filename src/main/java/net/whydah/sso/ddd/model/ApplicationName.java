package net.whydah.sso.ddd.model;


public class ApplicationName extends AbstractName {

	public ApplicationName(String name) {
		super(name);
	}
	
	public static boolean isValid(String name) {
		try {
			new ApplicationName(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}
