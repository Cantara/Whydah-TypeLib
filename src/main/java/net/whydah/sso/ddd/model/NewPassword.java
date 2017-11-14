package net.whydah.sso.ddd.model;


public class NewPassword extends AbstractName {
	
	public NewPassword(String inputNewpasswordToken) {
		super(inputNewpasswordToken);
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
	protected void validateInput(String input) {
		assertArgumentNotEmpty(input, "Attempt to create an illegal NewPassword - value is null or empty");
		assertArgumentLength(input, 3, 36, "Password must be 4-36 characters.");
		assertArgumentWithAPattern(input, "[^a-zA-Z0-9\\-]", "Password contains invalid characters: " + input);
	}

}