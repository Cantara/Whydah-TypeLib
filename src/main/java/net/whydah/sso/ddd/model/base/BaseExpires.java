package net.whydah.sso.ddd.model.base;

import net.whydah.sso.ddd.model.user.TimeStamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class BaseExpires extends BaseLifespan {

	public BaseExpires(String lifeCycleInMilliseconds, long max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	public BaseExpires(long lifeCycleInMilliseconds, long max_expiry_milisecs) {
		super(lifeCycleInMilliseconds, max_expiry_milisecs);
	}

	public long getValue() {
		return lifespanInMilliseconds + nowTimestamp;
	}
	
	public long getMillisecondValue() {
		return lifespanInMilliseconds + nowTimestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTimeoutInterval());
	}
	//    
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof BaseExpires)) {
			return false;
		}
		BaseExpires n = (BaseExpires) o;
		return Objects.equals(n.getTimeoutInterval(), getTimeoutInterval());
	}
	
}