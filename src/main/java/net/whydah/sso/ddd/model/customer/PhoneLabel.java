package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.ddd.model.base.AbstractName;

public class PhoneLabel extends AbstractName {

	public PhoneLabel(String name) {
		super(name);
	}
	
	public static boolean isValid(String name) {
		try {
			new PhoneLabel(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}