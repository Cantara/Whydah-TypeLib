package net.whydah.sso.ddd.model;


import net.whydah.sso.ddd.model.base.BaseExpires;

public class ApplicationTokenExpires extends BaseExpires {
    public ApplicationTokenExpires(long lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds);
	}

	public ApplicationTokenExpires(String lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds);
	}
	
	public ApplicationTokenExpires(long lifeCycleInMilliseconds, int max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	public ApplicationTokenExpires(String lifeCycleInMilliseconds, int max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	
	public static boolean isValid(String expiresToValidate) {
        try {
            new ApplicationTokenExpires(expiresToValidate);
            return true;
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