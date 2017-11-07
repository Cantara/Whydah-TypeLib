package net.whydah.sso.ddd.model;

import java.util.regex.Pattern;


public class ApplicationId extends AbstractId {

    Pattern p = Pattern.compile("[^a-zA-Z0-9\\-]");

    public ApplicationId(String id) {
    	super(id);
    }
    
    @Override
    protected void validateId(String anId) {
    	super.validateId(anId);
    	assertArgumentWithAPattern(anId, "[^a-zA-Z0-9\\-]", "Attempt to create an illegal WhydahIdentity - illegal characters" + anId);
    }

    public static boolean isValid(String appId) {
        try {
            new ApplicationId(appId);
            return true;
        } catch (Exception e) {
        }
        return false;
    }


//	@Override
//	protected int hashOddValue() {
//		return 8291;
//	}
//
//
//	@Override
//	protected int hashPrimeValue() {
//		return 220;
//	}
}