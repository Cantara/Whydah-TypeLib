package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class AddressLine2 extends AbstractName {

    public AddressLine2(String input) {
        super(input, 0, Validator.DEFAULT_MAX_LENGTH_250);
    }

    @Override
    protected void validateInput(String input) {
        super.validateInput(input);
        assertArgumentWithAPattern(input, Validator.DEFAULT_SENSIBLE_ADDRESSLINE, "Attempt to create an illegal RoleName - illegal characters: " + input);

//        super.assertArgumentWithSafeInput(input, 0, Validator.DEFAULT_MAX_LENGTH_102400, "The AddressLine2 " + input + " should not contain any invalid characters");

    }

    public static boolean isValid(String input) {
        try {
            new AddressLine2(input);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}