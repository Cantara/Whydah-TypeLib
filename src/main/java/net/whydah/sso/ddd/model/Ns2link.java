package net.whydah.sso.ddd.model;

public class Ns2link  extends AbstractUrl {

	public Ns2link(String input) {
		super(input);
	}
	
	public static boolean isValid(String input) {
		try {
			new Ns2link(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
