package net.whydah.sso.ddd.model.user;

import net.whydah.sso.ddd.model.base.ValueObject;

import java.util.Objects;

/**
 * The minimum security level for user and application tokens allowed to use application.
 * Legal security level values are: 0, 1, 2, 3, 4, 5
 * Default 0 - minimum security level is 0.  .
 */

public class SecurityLevel extends ValueObject {
	private int level=0;

	public SecurityLevel(int level){
		super(Integer.toString(level));
		assertArgumentRange(level, 0, 5, "Invalid number format: Security values should be in the range 0, 1, 2, 3, 4, 5");
		this.level = level;
	}

	public SecurityLevel(String level){
		super(level);
		if(isWhiteListed(level)){
			return;
		}
		try{
			int i = Integer.parseInt(level);
			assertArgumentRange(i, 0, 5, "Invalid number format: Security values should be in the range 0, 1, 2, 3, 4, 5");
			this.level = i;
		}catch(NumberFormatException ex ){
			throwException("Invalid number format: Security values should be in the range 0, 1, 2, 3, 4, 5");
		}	
	}

	@Override
	public String toString() {
		return String.valueOf(getLevel());
	}

	public int getLevel() {
		return level;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLevel());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof SecurityLevel)) {
			return false;
		}
		SecurityLevel n = (SecurityLevel) o;
		return Objects.equals(n.getLevel(), getLevel());
	}
	
	public static boolean isValid(String input) {
		try {
			new SecurityLevel(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean isValid(int input) {
		try {
			new SecurityLevel(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public String[] getWhiteList() {
		return null;
	}


}
