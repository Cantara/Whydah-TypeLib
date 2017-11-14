 package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.ValidationConfig;

public class RoleValue extends AbstractName {

	public RoleValue(String input) {
		//TODO: check later
		super(input, 0, ValidationConfig.DEFAULT_MAX_LENGTH_102400, false); //contain INNDATA in json format
	}

}
