package net.whydah.sso.ddd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastSeen extends TimeStamp {

	public LastSeen() {
		super(System.currentTimeMillis());
	}
	
	public LastSeen(String lastseen) {
		super(lastseen);
	}
	
	public LastSeen(long lastseen){
		super(lastseen);
	}
	
	@Override
	protected void validateInput(String ts, long time) {
		assertStateTrue(time <= System.currentTimeMillis(), "Illegal input: " + ts);
	}


	public static boolean isValid(String lastSeen) {
		try {
			new LastSeen(lastSeen);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isValid(long lastSeen) {
		try {
			new LastSeen(lastSeen);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}