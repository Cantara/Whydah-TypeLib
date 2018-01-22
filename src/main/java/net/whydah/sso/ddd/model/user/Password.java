package net.whydah.sso.ddd.model.user;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class Password extends AbstractName {
	
	public Password(String pwd) {
        super(pwd, 4, 128);
    }


	public static boolean isValid(String pwd) {
		try {
            new Password(pwd);
            return true;
		} catch (Exception e) {
		}
		return false;
	}

    @Override
    protected void validateInput(String input) {
        assertArgumentNotEmpty(input, "Attempt to create an illegal Password - value is null or empty");
        assertArgumentLength(input, 4, 128, "Password must be 4-128 characters.");
        assertArgumentWithAPattern(input, Validator.DEFAULT_PASSWORD_STRING, "Password contains invalid characters: " + input);
    }

    public static List<String> getInValidCharacters(String x){
    	
    	Pattern p = Pattern.compile(Validator.DEFAULT_PASSWORD_STRING);
    	List<String> invalidChars = new ArrayList<>();
 		for(int i = 0; i < x.length(); i++){
 			String in = x.substring(i, i+1);
 			Matcher m = p.matcher(in);
 			if(!m.find()){
 				invalidChars.add(in);
 			}
 		}
 		return invalidChars;
    }

}