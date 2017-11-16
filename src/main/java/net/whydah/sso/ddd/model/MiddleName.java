package net.whydah.sso.ddd.model;

import net.whydah.sso.ddd.model.base.AbstractName;

public class MiddleName extends AbstractName {
	
	public MiddleName(String name) {
		super(name);
	}
	
	public static boolean isValid(String input) {
		try {
			new MiddleName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
