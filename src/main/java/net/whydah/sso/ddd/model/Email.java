package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class Email extends AbstractName {

	public Email(String input) {
        super(input, 8, Validator.DEFAULT_MAX_LENGTH_1024); //long emails allowed
    }
	
	@Override
	protected void validateInput(String input) {
		super.validateInput(input);
		assertArgumentWithAPattern(input, Validator.DEFAULT_EMAIL_PATTERN,"Invalid email address " + input);
	}

	public static boolean isValid(String input) {
		try {
			new Email(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
}
