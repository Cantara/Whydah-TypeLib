package net.whydah.sso.ddd.model;

import net.whydah.sso.ddd.model.base.AbstractBaseId;

public class CustomerId extends AbstractBaseId {

    public CustomerId(String id) {
    	super(id);
    }
 
    public static boolean isValid(String appId) {
        try {
            new CustomerId(appId);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}