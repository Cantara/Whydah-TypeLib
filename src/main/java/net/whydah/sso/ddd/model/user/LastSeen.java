package net.whydah.sso.ddd.model.user;


import net.whydah.sso.ddd.model.TimeStamp;

public class LastSeen extends TimeStamp {


	public static final String[] WHITE_LIST = new String[]{ "not seen" };

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

		assertStateTrue(time <= System.currentTimeMillis(), "Illegal input, lastSeen can't be in the future: " + ts);

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
	
	@Override
	public String[] getWhiteList() {
		return new String[]{"not seen"};
	}
}