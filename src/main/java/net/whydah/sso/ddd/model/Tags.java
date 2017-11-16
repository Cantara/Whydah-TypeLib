package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;

public class Tags extends AbstractName{

	public Tags(String input) {
		super(input, 0, Validator.DEFAULT_MAX_LENGTH_10240);
	}
	
	public static boolean isValid(String input) {
		try {
			new Tags(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	

}
