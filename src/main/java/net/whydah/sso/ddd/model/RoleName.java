package net.whydah.sso.ddd.model;

import net.whydah.sso.ddd.model.base.AbstractName;

public class RoleName extends AbstractName {

	public RoleName(String input) {
        super(input, 0, 30, true);
    }

	public static boolean isValid(String input) {
		try {
			new RoleName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
