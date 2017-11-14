package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;

public class UserName extends AbstractName {

	public UserName(String name) {
		super(name);
	}


	
	@Override
	protected void validateInput(String name) {
		super.validateInput(name);
		boolean isEmail = Validator.isValidTextInput(name,0, maxLength, Validator.DEFAULT_EMAIL_PATTERN);
		if(!isEmail) {
			//no space allowed
			assertArgumentWithAPattern(name, Validator.DEFAULT_TEXT_WITH_LETTERS_NUMBERS_HYPHEN_UNDERSCORE, "Attempt to create an illegal username - illegal characters" + name);
		}
	}

	public static boolean isValid(String name) {
		try {
			new UserName(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}
