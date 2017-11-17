package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class Description extends AbstractName {
	
	public Description(String desc) {
		super(desc, 0, Validator.DEFAULT_MAX_LENGTH_1024);
	}
	
	public static boolean isValid(String input) {
		try {
			new Description(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	protected void validateInput(String anId) {
		super.validateInput(anId);
		assertArgumentWithAPattern(anId, Validator.DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_HYPHEN_PLUS_UNDERSCORE, "Attempt to create an illegal Description - illegal characters: " + anId);
		// assert min size, if not use DEFAULT_COMPANY_NAME  - aMessage....  to short CompanyName, replaces with default
	}
}