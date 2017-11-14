package net.whydah.sso.ddd.model;

public class PostalCity extends AbstractName {

	public PostalCity(String name) {
		super(name, 0, 30, true);
	}
	
	public static boolean isValid(String name) {
		try {
			new PhoneLabel(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}