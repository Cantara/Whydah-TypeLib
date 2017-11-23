package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.ddd.model.base.AbstractName;

public class TagName extends AbstractName {

	public TagName(String input) {
		super(input);
	}

	public static boolean isValid(String input) {
		try {
			new TagName(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
