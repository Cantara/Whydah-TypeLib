package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class LastName extends AbstractName {
	
	public LastName(String name) {
        super(name, 4, 60);
    }
	
	public static boolean isValid(String input) {
		try {
			new LastName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

    @Override
    protected void validateInput(String anId) {
        super.validateInput(anId);
        assertArgumentWithAPattern(anId, Validator.DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN_PLUS_UNDERSCORE, "Attempt to create an illegal LastName - illegal characters: " + anId);
        assertArgumentWithAPattern(anId, Validator.DEFAULT_SENSIBLE_PERSON_NAME, "Attempt to create an illegal LastName - not a sensible name: " + anId);

    }

}
