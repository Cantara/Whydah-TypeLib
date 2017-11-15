package net.whydah.sso.ddd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class TimeStamp extends ValueObject {

	protected long timestamp=-34343434;


	public TimeStamp(String ts) {
		if(ts==null || ts.trim().equals("")){
			ts = Long.toString(System.currentTimeMillis());
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date parsedDate;		
		if(ts.contains("-") && ts.contains(" ")){			
			try {
				parsedDate = dateFormat.parse(ts);
				validateInput(ts, parsedDate.getTime());
				timestamp = parsedDate.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
				throwException("Attempt to create an illegal timestamp: " + ts);
			}
    	} else {
    		
    		if(ts.length()>=10){
    			try {
    				parsedDate = new Date(Long.parseLong(ts));
    				validateInput(ts, parsedDate.getTime());
    				timestamp = parsedDate.getTime();
    			} catch (NumberFormatException e) {
    				e.printStackTrace();
    				throwException("Attempt to create an illegal timestamp: " + ts);
    			}
    		} else {
    			throwException("Attempt to create an illegal timestamp: " + ts);
    		}
    	}
		
	}

	public TimeStamp(long ts) {
		validateInput(null, ts);
		timestamp = ts;
	}


	public long getValue() {
		return timestamp;
	}

	public long getSecondValue() {
		return timestamp / 1000;
	}

	protected void validateInput(String timeInput, long timeInputParsed){
    	
    }

	@Override
	public String toString() {
		return Long.toString(timestamp);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof TimeStamp)) {
			return false;
		}
		TimeStamp n = (TimeStamp) o;
		return Objects.equals(n.getValue(), getValue());
	}
	
	public static boolean isValid(String input) {
		try {
			new TimeStamp(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean isValid(long input) {
		try {
			new TimeStamp(input);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}