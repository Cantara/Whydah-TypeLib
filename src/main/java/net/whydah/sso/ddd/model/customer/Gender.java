package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class Gender extends AbstractName {
	
	public Gender(String name) {
		super(name, 0, 8);
	}

    @Override
    protected void validateInput(String name) {
        super.validateInput(name);
        assertArgumentWithAPattern(name, Validator.DEFAULT_SENSIBLE_USERNAME, "Attempt to create an illegal Gender - illegal characters" + name);
    }

	public static boolean isValid(String input) {
		try {
			new Gender(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}

