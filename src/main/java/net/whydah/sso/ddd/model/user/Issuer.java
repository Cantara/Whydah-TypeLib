package net.whydah.sso.ddd.model.user;

import net.whydah.sso.ddd.model.base.AbstractUrl;

public class Issuer extends AbstractUrl {
	
	public Issuer(String name) {
		super(name, true);
	}


    @Override
    protected void validateInput(String input) {
        super.validateInput(input);
        assertArgumentWithSafeInput(input, 3, 136, null, new String[]{"(", ")", "'", "[", "]", ",", "*"}, "The Issuer must have the length 3 - 136 and without invalid characters.");

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

