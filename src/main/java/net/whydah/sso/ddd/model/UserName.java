package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class UserName extends AbstractName {

	public UserName(String name) {
		super(name, 4, 50);
	}


	
	@Override
	protected void validateInput(String name) {
		super.validateInput(name);
		boolean isEmail = Validator.isValidTextInput(name,0, maxLength, Validator.DEFAULT_EMAIL_PATTERN);
		if(!isEmail) {
			//no space allowed
			assertArgumentWithAPattern(name, Validator.DEFAULT_SENSIBLE_USERNAME, "Attempt to create an illegal username - illegal characters" + name);
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
