package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class FirstName extends AbstractName {
	
	public FirstName(String name) {
        super(name, 2, 30);
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
    protected void validateInput(String anId) {
        super.validateInput(anId);
        assertArgumentWithAPattern(anId, Validator.DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN_PLUS_UNDERSCORE, "Attempt to create an illegal FirstName - illegal characters: " + anId);
//        assertArgumentWithAPattern(anId, Validator.NEED_TO_CONTAIN_SENSIBLE_NAME_WITH_CHARACTERS, "Attempt to create an illegal FirstName - not a sensible name: " + anId);
    }

}

