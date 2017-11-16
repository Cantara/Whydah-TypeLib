package net.whydah.sso.ddd.model;

import net.whydah.sso.ddd.model.base.AbstractBaseId;

public class UID extends AbstractBaseId {

    public UID(String id) {
		super(id, 4, 36); //it is ok some for some identity structures to not have userId (yet)
	}

    public static boolean isValid(String input) {
		try {
			new UID(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
