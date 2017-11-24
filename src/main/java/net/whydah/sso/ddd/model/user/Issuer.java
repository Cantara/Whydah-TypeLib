package net.whydah.sso.ddd.model.user;

import net.whydah.sso.ddd.model.base.AbstractUrl;

public class Issuer extends AbstractUrl {
	
	public Issuer(String name) {
		super(name, true);
	}


    @Override
    protected void validateInput(String input) {
        super.validateInput(input);
    }

	public static boolean isValid(String input) {
		try {
			new Issuer(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}

