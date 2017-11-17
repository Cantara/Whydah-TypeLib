package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class PersonRef extends AbstractName {

    public PersonRef(String name) {
		super(name);
	}


    @Override
    protected void validateInput(String name) {
        super.validateInput(name);

        assertArgumentWithAPattern(name, Validator.DEFAULT_SENSIBLE_REFERENCE, "Attempt to create an illegal PersonRef - illegal characters: " + name);

    }


	public static boolean isValid(String name) {
		try {
			new PersonRef(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}