package net.whydah.sso.ddd.model;

import java.util.Objects;


public class SessionTimeout extends ValueObject {

	long _timeout;
	
	public SessionTimeout(String timeout) {
		BaseExpires expiryDate = new BaseExpires(timeout);
		_timeout = expiryDate.getTimeoutInterval();
	}
	
	public SessionTimeout(long timeout) {
		BaseExpires expiryDate = new BaseExpires(timeout);
		_timeout = expiryDate.getTimeoutInterval();
	}

	public long getValue(){
		return _timeout;
	}
	
	public long getValueInSeconds(){
		return _timeout/1000;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof SessionTimeout)) {
			return false;
		}
		SessionTimeout n = (SessionTimeout) o;
		return Objects.equals(n.getValue(), getValue());
	}
	
	public static boolean isValid(String input) {
		try {
			new SessionTimeout(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
