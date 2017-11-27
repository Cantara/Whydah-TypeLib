package net.whydah.sso.ddd.model.application;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class DefaultRoleNames extends AbstractName {

    public DefaultRoleNames(String input) {
        super(input, 0, 90, false);
    }

    @Override
    protected void validateInput(String name) {
        super.validateInput(name);
        boolean isEmail = Validator.isValidTextInput(name, 0, maxLength, Validator.DEFAULT_EMAIL_PATTERN);
        if (!isEmail) {

            assertArgumentWithAPattern(name, Validator.DEFAULT_SENSIBLE_ROLENAMES, "Attempt to create an illegal DefaultRoleNames - illegal characters: " + name);
        }
    }

    public static boolean isValid(String input) {
        try {
            new DefaultRoleNames(input);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}
