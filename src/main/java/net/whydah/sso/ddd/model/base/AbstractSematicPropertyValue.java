package net.whydah.sso.ddd.model.base;

import net.whydah.sso.basehelpers.Validator;

public class AbstractSematicPropertyValue extends AbstractName {

    public AbstractSematicPropertyValue(String input) {
        this(input, Validator.DEFAULT_CHECK_INVALID_HTML_USE);
    }

    public AbstractSematicPropertyValue(String input, boolean checkSafeInput) {
        this(input, 0, Validator.DEFAULT_MAX_LENGTH_250, checkSafeInput);

    }

    public AbstractSematicPropertyValue(String input, int minLength, int maxLength) {
        this(input, minLength, maxLength, Validator.DEFAULT_CHECK_INVALID_HTML_USE);
    }


    public AbstractSematicPropertyValue(String input, int minLength, int maxLength, boolean checkSafeInput) {
        super(input, minLength, maxLength, checkSafeInput);

    }

    public AbstractSematicPropertyValue(String input, int minLength, int maxLength, String defaultValue) {
        super(input, minLength, maxLength, defaultValue);
    }

    @Override
    protected void validateInput(String name) {
        super.validateInput(name);
        //We're in embeddded json land
        if (name.contains("<?xml") || name.contains("<![CDATA")) {
            this.assertArgumentWithSafeXMLInput(name, minLength, maxLength, "Attempt to create an illegal RoleValue input: " + name);
        } else {
            this.assertArgumentWithSafeJsonInput(name, minLength, maxLength, "Attempt to create an illegal RoleValue input: " + name);
        }

    }

    public static boolean isValid(String input) {
        try {
            new AbstractSematicPropertyValue(input);
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return false;
    }
}
