package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractUrl;

public class LogoUrl extends AbstractUrl {

	public LogoUrl(String input) {
		super(input);
	}
	
	@Override
	protected void validateInput(String input) {
		boolean isBase64 = false;
		if(input!=null && input.length()>0){
			isBase64 = Validator.isValidTextInput(input, 1, Integer.MAX_VALUE, Validator.DEFAULT_IMAGE_BASE64_PATTTERN);			
		}
		if(!isBase64){
			super.validateInput(input);
		}
	}

}
