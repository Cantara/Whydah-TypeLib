package net.whydah.sso.ddd.model.userrole;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

 public class RoleValue extends AbstractName {

	public RoleValue(String input) {
		//TODO: check later
		super(input, 0, Validator.DEFAULT_MAX_LENGTH_102400, false); //allowed to contain application in json format
	}
	
	public static boolean isValid(String input) {
		try {
			new RoleValue(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}