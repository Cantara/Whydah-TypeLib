package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class CustomerId extends AbstractName {

    public CustomerId(String id) {
        super(id, 0, 70);
    }
 
    public static boolean isValid(String appId) {
        try {
            new CustomerId(appId);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    protected void validateInput(String reference) {
        super.validateInput(reference);

        assertArgumentWithAPattern(reference, Validator.DEFAULT_SENSIBLE_REFERENCE, "Attempt to create an illegal CustomerId - illegal characters: " + reference);

    }


}