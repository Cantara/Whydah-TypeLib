package net.whydah.sso.ddd.model.customer;


import net.whydah.sso.ddd.model.base.AbstractName;

public class EmailLabel extends AbstractName {

	public EmailLabel(String name) {
		super(name);
	}

	public static boolean isValid(String name) {
		try {
			new EmailLabel(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}