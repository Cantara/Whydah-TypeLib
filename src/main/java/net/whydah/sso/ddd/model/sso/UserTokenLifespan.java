package net.whydah.sso.ddd.model.sso;

import net.whydah.sso.ddd.model.base.BaseLifespan;

import java.util.Calendar;


public class UserTokenLifespan extends BaseLifespan {

	
	public UserTokenLifespan(long lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds, getDefaultTimeout(18)); //18 months max
	}

	public UserTokenLifespan(String lifeCycleInMilliseconds) {
		super(lifeCycleInMilliseconds, getDefaultTimeout(18));
	}

	public UserTokenLifespan(long lifeCycleInMilliseconds, long max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	public UserTokenLifespan(String lifeCycleInMilliseconds, long max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	public static boolean isValid(String lifeCycleInMilliseconds) {
		try {
			UserTokenLifespan userTokenLifespan = new UserTokenLifespan(lifeCycleInMilliseconds);
			return userTokenLifespan.isValid();
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

	public static boolean isValid(String lifeCycleInMilliseconds, long max_expiry_milisecs) {
		try {
			new UserTokenLifespan(lifeCycleInMilliseconds, max_expiry_milisecs);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isValid(long lifeCycleInMilliseconds, long max_expiry_milisecs) {
		try {
			new UserTokenLifespan(lifeCycleInMilliseconds, max_expiry_milisecs);
			return true;
		} catch (Exception e) {
		}
		return false;
	}


}