package net.whydah.sso.ddd.model.userrole;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class RoleValue extends AbstractName {

    public RoleValue(String input) {
        super(input, 0, Validator.DEFAULT_MAX_LENGTH_102400, true); //allowed to contain application in json format
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
            new RoleValue(input);
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return false;
    }

}
