package net.whydah.sso.ddd.model.application;

import net.whydah.sso.ddd.model.base.AbstractBaseId;


public class ApplicationId extends AbstractBaseId {

    public ApplicationId(String id) {
    	super(id, 2, 36); //test fails when minLength=3
    }

    public static boolean isValid(String appId) {
        try {
            new ApplicationId(appId);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}