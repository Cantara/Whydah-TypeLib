package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractJSON;

public class AddressLine1 extends AbstractJSON {

    public AddressLine1(String input) {
        super(input, 0, Validator.DEFAULT_MAX_LENGTH_102400);
	}
	
	@Override
	protected void validateInput(String input) {
		super.validateInput(input);
        super.assertArgumentWithSafeJsonInput(input, 0, Validator.DEFAULT_MAX_LENGTH_102400, "The AddressLine1 " + input + " should not contain any invalid characters");

	}
	
	public static boolean isValid(String input) {
		try {
            new AddressLine1(input);
            return true;
		} catch (Exception e) {
		}
		return false;
	}
}