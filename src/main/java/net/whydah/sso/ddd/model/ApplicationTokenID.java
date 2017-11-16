package net.whydah.sso.ddd.model;


import net.whydah.sso.ddd.model.base.AbstractBaseId;

public class ApplicationTokenID extends AbstractBaseId {

    public ApplicationTokenID(String applicationTokenId) {
        super(applicationTokenId);
    }
    
    public static boolean isValid(String input) {
		try {
			new ApplicationTokenID(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}


}
