package net.whydah.sso.ddd.model.user;


import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class Password extends AbstractName {
	
	public Password(String pwd) {
        super(pwd, 4, 66);
    }


	public static boolean isValid(String pwd) {
		try {
            new Password(pwd);
            return true;
		} catch (Exception e) {
		}
		return false;
	}

    @Override
    protected void validateInput(String input) {
        assertArgumentNotEmpty(input, "Attempt to create an illegal Password - value is null or empty");
        assertArgumentLength(input, 4, 60, "Password must be 4-36 characters.");
        assertArgumentWithAPattern(input, Validator.DEFAULT_PASSWORD_STRING, "Password contains invalid characters: " + input);
    }


}