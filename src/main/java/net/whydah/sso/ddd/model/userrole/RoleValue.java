package net.whydah.sso.ddd.model.userrole;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractSematicPropertyValue;

public class RoleValue extends AbstractSematicPropertyValue {

    public RoleValue(String input) {
        super(input, 0, Validator.DEFAULT_MAX_LENGTH_102400, true); //allowed to contain application in json format
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
