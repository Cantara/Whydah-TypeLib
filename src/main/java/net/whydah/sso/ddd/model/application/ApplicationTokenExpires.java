package net.whydah.sso.ddd.model.application;


import net.whydah.sso.ddd.model.base.BaseExpires;

public class ApplicationTokenExpires extends BaseExpires {
    public ApplicationTokenExpires(long expiresInMilliseconds) {
        super(expiresInMilliseconds, 5000000000L);
    }

    public ApplicationTokenExpires(String expiresInMilliseconds) {
        super(expiresInMilliseconds, 5000000000L);
    }
	
	public ApplicationTokenExpires(long lifeCycleInMilliseconds, int max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	public ApplicationTokenExpires(String lifeCycleInMilliseconds, int max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	
	public static boolean isValid(String expiresToValidate) {
        try {
            ApplicationTokenExpires applicationTokenExpires = new ApplicationTokenExpires(expiresToValidate);
            return applicationTokenExpires.isvalid();
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isValid(long expiresToValidate) {
        try {
            new ApplicationTokenExpires(expiresToValidate);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public static boolean isValid(String expiresToValidate, int max_expiry_milisecs) {
        try {
            new ApplicationTokenExpires(expiresToValidate, max_expiry_milisecs);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isValid(long expiresToValidate, int max_expiry_milisecs) {
        try {
            new ApplicationTokenExpires(expiresToValidate, max_expiry_milisecs);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}