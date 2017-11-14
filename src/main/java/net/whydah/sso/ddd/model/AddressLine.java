package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.ValidationConfig;

public class AddressLine extends AbstractName {
	
	public AddressLine(String input) {
		super(input, 0, ValidationConfig.DEFAULT_MAX_LENGTH_102400);
	}
	
	@Override
	protected void validateInput(String input) {
		super.validateInput(input);
		super.assertArgumentWithSafeInput(input, 0, ValidationConfig.DEFAULT_MAX_LENGTH_102400, "The input should not contain any invalid characters");
	}
}