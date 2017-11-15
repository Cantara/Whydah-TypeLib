package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;

public class CellPhone extends AbstractName {

	public CellPhone(String input) {
		super(input);
	}
	
	@Override
	protected void validateInput(String input) {
		super.validateInput(input);
		assertArgumentWithAPattern(input, Validator.DEFAULT_PHONE_NUMBER_PATTERN, "Invalid phone number " + input);
	}
	
	public static boolean isValid(String input) {
		try {
			new CellPhone(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}


	
}
