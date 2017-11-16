package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class ApplicationPath extends AbstractName {

	public ApplicationPath(String input) {
		super(input, 0, Validator.DEFAULT_MAX_LENGTH_10240); //allow long paths
	}

	public static boolean isValid(String input) {
		try {
			new ApplicationPath(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
