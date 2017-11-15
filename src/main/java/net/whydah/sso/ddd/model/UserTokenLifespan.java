package net.whydah.sso.ddd.model;



public class UserTokenLifespan extends BaseExpires {

	public UserTokenLifespan(long lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds);
	}
	
	public UserTokenLifespan(String lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds);
	}
	
	public UserTokenLifespan(long lifeCycleInMilliseconds, int max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}
	
	public UserTokenLifespan(String lifeCycleInMilliseconds, int max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}
	
	public static boolean isValid(String lifeCycleInMilliseconds) {
        try {
            new UserTokenLifespan(lifeCycleInMilliseconds);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isValid(long lifeCycleInMilliseconds) {
        try {
            new UserTokenLifespan(lifeCycleInMilliseconds);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
	
}