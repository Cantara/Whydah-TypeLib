package net.whydah.sso.ddd.model.user;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class PersonRef extends AbstractName {

	public PersonRef(String reference) {
		super(reference);
	}


    @Override
	protected void validateInput(String reference) {
		super.validateInput(reference);

		assertArgumentWithAPattern(reference, Validator.DEFAULT_SENSIBLE_REFERENCE, "Attempt to create an illegal PersonRef - illegal characters: " + reference);

    }


	public static boolean isValid(String reference) {
		try {
			new PersonRef(reference);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}