package net.whydah.sso.ddd.model;



public class ApplicationTokenExpires extends BaseExpires{
	public ApplicationTokenExpires(long lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds);
	}

	public ApplicationTokenExpires(String lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds);
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

}