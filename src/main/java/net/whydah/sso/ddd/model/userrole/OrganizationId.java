package net.whydah.sso.ddd.model.userrole;

import net.whydah.sso.ddd.model.base.AbstractBaseId;

public class OrganizationId extends AbstractBaseId {

	public OrganizationId(String anId) {
		super(anId, 0, 36);
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
