package net.whydah.sso.ddd.model;

import net.whydah.sso.ddd.model.base.AbstractName;

public class Gender extends AbstractName {
	
	public Gender(String name) {
		super(name, 0, 8);
	}
	
	public static boolean isValid(String input) {
		try {
			new Gender(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}

