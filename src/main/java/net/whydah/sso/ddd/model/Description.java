package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.ValidationConfig;

public class Description extends AbstractName {
	
	public Description(String desc) {
		super(desc, 0, ValidationConfig.DEFAULT_MAX_LENGTH_1024);
	}
}