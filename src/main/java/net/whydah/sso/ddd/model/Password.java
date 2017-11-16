package net.whydah.sso.ddd.model;


import net.whydah.sso.ddd.model.base.AbstractName;

public class Password extends AbstractName {
	
	public Password(String pwd) {
		super(pwd, 4, 36);
	}


	public static boolean isValid(String pwd) {
		try {
			new NewPassword(pwd);
			return true;
		} catch (Exception e) {
		}
		return false;
	}


}