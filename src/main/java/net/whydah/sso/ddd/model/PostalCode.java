package net.whydah.sso.ddd.model;

import net.whydah.sso.ddd.model.base.AbstractName;

public class PostalCode extends AbstractName {

	public PostalCode(String name) {
		super(name, 0, 30, true);
	}
	
	public static boolean isValid(String name) {
		try {
			new PostalCode(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}