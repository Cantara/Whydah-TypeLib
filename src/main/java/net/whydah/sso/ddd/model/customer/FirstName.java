package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class FirstName extends AbstractName {
	
	public FirstName(String name) {
        super(name, 2, 80);
    }
	
	public static boolean isValid(String input) {
		try {
			new FirstName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

    @Override
    protected void validateInput(String aPersonName) {
        super.validateInput(aPersonName);
        assertArgumentWithAPattern(aPersonName, Validator.DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN_PLUS_UNDERSCORE, "Attempt to create an illegal FirstName - illegal characters: " + aPersonName);
        assertArgumentWithAPattern(aPersonName, Validator.DEFAULT_SENSIBLE_PERSON_NAME, "Attempt to create an illegal FirstName - not a sensible name: " + aPersonName);
    }

}

