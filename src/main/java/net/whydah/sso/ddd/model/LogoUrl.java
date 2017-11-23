package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractUrl;

/**
 * LogoURL is currently a polyglot field, consisting of two variations
 * <p>
 * a) an URL as the fieldname suggests
 * b) suport for data:image/png;base64 embedded logo images
 */
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

    public static boolean isValid(String input) {
        try {
            new LogoUrl(input);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
