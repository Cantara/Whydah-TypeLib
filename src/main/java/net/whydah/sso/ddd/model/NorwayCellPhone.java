package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;

public class NorwayCellPhone extends CellPhone {

    public NorwayCellPhone(String input) {
        super(input.replaceAll(" ", ""));
    }

    @Override
    protected void validateInput(String input) {
        super.validateInput(input);
        assertArgumentWithAPattern(input, Validator.DEFAULT_NORWAY_CELLPHONE_NUMBER_PATTERN, "Invalid Norway cellphone number " + input);
    }

    public static boolean isValid(String input) {
        try {
            new NorwayCellPhone(input);
            return true;
        } catch (Exception e) {
        }
        return false;
    }


}
