package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;


public class ApplicationSecret extends AbstractName {
	
	public ApplicationSecret(String secret) {
		super(secret, 8, 80);
	}

	public static boolean isValid(String secret) {
		try {
			new ApplicationSecret(secret);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	@Override
	protected void validateInput(String anId) {
		super.validateInput(anId);
		assertArgumentWithAPattern(anId, Validator.DEFAULT_PASSWORD_STRING, "Attempt to create an illegal ApplicationSecret - illegal characters: " + anId);

	}
	
}
