package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class AddressLine extends AbstractName {
	
	public AddressLine(String input) {
		super(input, 0, Validator.DEFAULT_MAX_LENGTH_102400);
	}
	
	@Override
	protected void validateInput(String input) {
		super.validateInput(input);
		super.assertArgumentWithSafeInput(input, 0, Validator.DEFAULT_MAX_LENGTH_102400, "The input" + input + " should not contain any invalid characters");
	}
	
	public static boolean isValid(String input) {
		try {
			new AddressLine(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}