package net.whydah.sso.ddd.model;

public class AddressLabel extends AbstractName {

	public AddressLabel(String input) {
		super(input);
	}

	public static boolean isValid(String input) {
		try {
			new AddressLabel(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
