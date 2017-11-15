package net.whydah.sso.ddd.model;

import com.ctc.wstx.api.ValidatorConfig;

import net.whydah.sso.basehelpers.ValidationConfig;
import net.whydah.sso.basehelpers.Validator;

public class Email extends AbstractName {

	public Email(String input) {
		super(input, 0, ValidationConfig.DEFAULT_MAX_LENGTH_1024); //long emails allowed
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
