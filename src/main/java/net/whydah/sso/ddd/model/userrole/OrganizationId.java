package net.whydah.sso.ddd.model.userrole;

import net.whydah.sso.ddd.model.base.AbstractId;

public class OrganizationId extends AbstractId {

	public OrganizationId(String anId) {
		super(anId);
	}

	public static boolean isValid(String input) {
		try {
			new OrganizationId(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
