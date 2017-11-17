package net.whydah.sso.ddd.model;

import net.whydah.sso.ddd.model.base.AbstractName;

public class NewPasswordToken extends AbstractName {

    public NewPasswordToken(String inputNewpasswordToken) {
        super(inputNewpasswordToken);
    }


    public static boolean isValid(String passwordTokenToValidate) {
        try {
            new NewPasswordToken(passwordTokenToValidate);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    protected void validateInput(String input) {
        assertArgumentNotEmpty(input, "Attempt to create an illegal NewPasswordToken - value is null or empty");
        assertArgumentLength(input, 3, 36, "NewPasswordToken must be 4-36 characters.");
        assertArgumentWithAPattern(input, "[^a-zA-Z0-9\\-]", "NewPasswordToken contains invalid characters: " + input);
    }

}