package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.ddd.model.base.AbstractName;

public class TagName extends AbstractName {

	private static final long serialVersionUID = 1L;

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
