package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.ddd.model.base.AbstractName;

public class TagValue extends AbstractName {

	public TagValue(String input) {
		super(input);
	}

	public static boolean isValid(String input) {
		try {
			new TagValue(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
