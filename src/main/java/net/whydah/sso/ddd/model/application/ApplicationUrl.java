package net.whydah.sso.ddd.model.application;

import net.whydah.sso.ddd.model.base.AbstractUrl;

public class ApplicationUrl extends AbstractUrl {

	public ApplicationUrl(String input) {
		super(input);
	}

	public static boolean isValid(String input) {
		try {
            new ApplicationUrl(input.trim());
            return true;
		} catch (Exception e) {
		}
		return false;
	}


}

