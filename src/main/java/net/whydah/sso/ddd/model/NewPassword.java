package net.whydah.sso.ddd.model;


@lombok.EqualsAndHashCode(of = {"newPasswordToken"})
public class NewPassword extends ValueObject {

	private String newPasswordToken;
	//private final static Logger log = LoggerFactory.getLogger(WhydahIdentity.class);
	//Pattern p = Pattern.compile("[^a-zA-Z0-9\\-]");

	public NewPassword(String inputNewpasswordToken) {

		//        // Must not be null
		//        if (inputNewpasswordToken == null) {
		//            log.error("Attempt to create an illegal NewPassword - value is null");
		//            this.newPasswordToken = null;
		//            // Must be a string, min length=3, max length 36
		//        } else if (inputNewpasswordToken.length() < 3 || inputNewpasswordToken.length() > 36) {
		//            log.error("Attempt to create an illegal NewPassword - illegal length:{}", inputNewpasswordToken.length());
		//            this.newPasswordToken = null;
		//            // Must be of only whitelisted characters
		//        } else if (p.matcher(inputNewpasswordToken).find()) {
		//            log.error("Attempt to create an illegal NewPassword - illegal characters - inputNewpasswordToken:{}", inputNewpasswordToken);
		//            this.newPasswordToken = null;
		//        } else {
		//            this.newPasswordToken = inputNewpasswordToken;
		//        }
		//        
		assertArgumentNotEmpty(inputNewpasswordToken, "Attempt to create an illegal NewPassword - value is null or empty");
		assertArgumentLength(inputNewpasswordToken, 3, 36, "Password must be 4-36 characters.");
		assertArgumentWithAPattern(inputNewpasswordToken, "[^a-zA-Z0-9\\-]", "Password contains invalid characters: " + inputNewpasswordToken);
		this.newPasswordToken = inputNewpasswordToken;
	}


	public String getNewPasswordToken() {
		return newPasswordToken;
	}


	public static boolean isValid(String passwordTokenToValidate) {
		try {
			new NewPassword(passwordTokenToValidate);
			return true;
		} catch (Exception e) {
		}
		return false;
	}


	@Override
	public String toString() {
		return newPasswordToken;
	}

	//    @Override
	//	 public boolean equals(Object anObject) {
		//		 boolean equalObjects = false;
	//
	//		 if (anObject != null && this.getClass() == anObject.getClass()) {
	//			 NewPassword typedObject = (NewPassword) anObject;
	//			 equalObjects = this.getNewPasswordToken().equals(typedObject.getNewPasswordToken());
	//		 }
	//
	//		 return equalObjects;
	//	 }
	//
	//	    @Override
	//	    public int hashCode() {
	//	        int hashCodeValue =
	//	            + (3835 * 220)
	//	            + this.getNewPasswordToken().hashCode();
	//
	//	        return hashCodeValue;
	//	    }
}