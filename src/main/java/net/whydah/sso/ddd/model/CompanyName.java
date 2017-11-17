package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class CompanyName extends AbstractName {

    public static final String DEFAULT_COMPANY_NAME = "Not Set";

    public CompanyName(String name) {
        super(name, 0, 120);
    }
	
	public static boolean isValid(String input) {
		try {
			new CompanyName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	protected void validateInput(String anId) {
		super.validateInput(anId);
        assertArgumentWithAPattern(anId, Validator.DEFAULT_TEXT_WITH_LETTERS_NUMBERS_SPACE_UNDERSCORE, "Attempt to create an illegal CompanyName - illegal characters: " + anId);
        // assert min size, if not use DEFAULT_COMPANY_NAME  - aMessage....  to short CompanyName, replaces with default
    }
}
