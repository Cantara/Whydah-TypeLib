package net.whydah.sso.ddd.model.user;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class CellPhone extends AbstractName {

	public CellPhone(String input) {
        super(input);
    }
	
	@Override
	protected void validateInput(String input) {
		super.validateInput(input);
        assertArgumentWithAPattern(input.replaceAll("[\\+\\.\\s\\-\\)\\(]", ""), Validator.DEFAULT_CELLPHONE_NUMBER_PATTERN, "Invalid cellphone number " + input);
    }
	
	public static boolean isValid(String input) {
		try {
			new CellPhone(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public String getValue(){
		return _input.replaceAll("[\\.\\s\\-\\)\\(]", "");
	}


	
}
