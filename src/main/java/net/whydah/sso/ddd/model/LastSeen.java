package net.whydah.sso.ddd.model;


public class LastSeen extends TimeStamp {


    public static final String NOT_SEEN = "Not seen";

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
//            if (NOT_SEEN.equalsIgnoreCase(lastSeen)){
//                return true;
//            }
            LastSeen lastSeen1 = new LastSeen(lastSeen);
            lastSeen1.validateInput(lastSeen, Long.parseLong(lastSeen));
            return true;
        } catch (Exception e) {
		}
		return false;
	}

	public static boolean isValid(long lastSeen) {
		try {
            LastSeen lastSeen1 = new LastSeen(lastSeen);
            lastSeen1.validateInput(Long.toString(lastSeen), lastSeen);
            return true;
        } catch (Exception e) {
		}
		return false;
	}
}