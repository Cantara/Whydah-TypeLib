package net.whydah.sso.ddd.model;

public class PersonRef extends AbstractName {

    public PersonRef(String name) {
		super(name);
	}


	public static boolean isValid(String name) {
		try {
			new PersonRef(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}