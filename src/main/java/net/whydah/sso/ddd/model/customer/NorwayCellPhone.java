package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.user.CellPhone;

public class NorwayCellPhone extends CellPhone {

    public NorwayCellPhone(String input) {
        super(input);
    }

    @Override
    protected void validateInput(String input) {
        super.validateInput(input);
        assertArgumentWithAPattern(input.replaceAll("[\\+\\.\\s\\-\\)\\(]", ""), Validator.DEFAULT_NORWAY_CELLPHONE_NUMBER_PATTERN, "Invalid Norway cellphone number " + input);
    }

    public static boolean isValid(String input) {
        try {
            new NorwayCellPhone(input);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public String getValue() {
    	String number= super.getValue();
    	
    	if(!number.startsWith("47")){
    		number += "+47" + number;
    	}
    	return number;
    }


}
