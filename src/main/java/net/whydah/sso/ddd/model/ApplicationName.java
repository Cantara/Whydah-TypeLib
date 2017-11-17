package net.whydah.sso.ddd.model;


import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class ApplicationName extends AbstractName {

	public ApplicationName(String name) {
        super(name, 0, 50);
    }


    @Override
    protected void validateInput(String name) {
        super.validateInput(name);

        assertArgumentWithAPattern(name, Validator.DEFAULT_SENSIBLE_APPLICATIONNAME, "Attempt to create an illegal ApplicationName - illegal characters: " + name);

    }

	public static boolean isValid(String name) {
		try {
			new ApplicationName(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}
