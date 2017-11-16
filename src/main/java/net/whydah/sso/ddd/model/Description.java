package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class Description extends AbstractName {
	
	public Description(String desc) {
		super(desc, 0, Validator.DEFAULT_MAX_LENGTH_1024);
	}
	
	public static boolean isValid(String input) {
		try {
			new Description(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}