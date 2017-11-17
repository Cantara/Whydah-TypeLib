package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class RoleName extends AbstractName {

	public RoleName(String input) {
        super(input, 2, 60, true);
    }

    @Override
    protected void validateInput(String name) {
        super.validateInput(name);
        boolean isEmail = Validator.isValidTextInput(name, 0, maxLength, Validator.DEFAULT_EMAIL_PATTERN);
        if (!isEmail) {
            //no space allowed
            assertArgumentWithAPattern(name, Validator.DEFAULT_SENSIBLE_USERNAME, "Attempt to create an illegal RoleName - illegal characters: " + name);
        }
    }

	public static boolean isValid(String input) {
		try {
			new RoleName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
