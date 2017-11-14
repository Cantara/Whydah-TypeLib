package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.ValidationConfig;

public class Tags extends AbstractName{

	public Tags(String input) {
		super(input, 0, ValidationConfig.DEFAULT_MAX_LENGTH_10240);
	}
	
	

}
