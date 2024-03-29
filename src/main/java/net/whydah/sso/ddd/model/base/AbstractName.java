package net.whydah.sso.ddd.model.base;

import net.whydah.sso.basehelpers.Validator;
import org.jsoup.safety.Safelist;

import java.util.Objects;


public class AbstractName extends ValueObject {

    private static final long serialVersionUID = 1L;

    
    protected int maxLength = Validator.DEFAULT_MAX_LENGTH_250;
    protected int minLength = 0; //allow empty
    protected boolean checkSafeInput = Validator.DEFAULT_CHECK_INVALID_HTML_USE; //check length and content
    
   

    @Override
    public String toString() {
        return this._input;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(_input);
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o == this) return true;
        if (!(o instanceof AbstractName)) {
            return false;
        }
        AbstractName n = (AbstractName) o;
        return Objects.equals(n.getInput(), _input);
    }

    public AbstractName(String input) {
    	this(input, Validator.DEFAULT_CHECK_INVALID_HTML_USE);
    }
    
    public AbstractName(String input, boolean checkSafeInput) {
    	this(input, 0, Validator.DEFAULT_MAX_LENGTH_250, checkSafeInput);
       
    }
    
    public AbstractName(String input, int minLength, int maxLength) {
       this(input, minLength, maxLength, Validator.DEFAULT_CHECK_INVALID_HTML_USE);
    }
    
    
    
    public AbstractName(String input, int minLength, int maxLength, boolean checkSafeInput) {
        super(input);
        if(isWhiteListed(input)){
        	return;
        }
        this.checkSafeInput = checkSafeInput;
        this.maxLength = maxLength;
        this.minLength = minLength;
        if(input!=null&&!input.equalsIgnoreCase("null")){
        	if(!input.equals("") || minLength!=0){
				this.validateInput(input);
			}
        	
        }
        
    }

    public AbstractName(String input, int minLength, int maxLength, String defaultValue) {
        super(input);
        if (isWhiteListed(input)) {
            return;
        }
        this.maxLength = maxLength;
        this.minLength = minLength;
        if (input != null && !input.equalsIgnoreCase("null")) {
            if (!input.equals("") || minLength != 0) {
                this.validateInput(input);
            }

        }

    }


    protected void validateInput(String input){
    	 if(!checkSafeInput) { //only check length
             this.assertArgumentLength(input, minLength, maxLength, "The input's length must be " + String.valueOf(minLength) + "-" + String.valueOf(maxLength) + ". Got: " + input.length());
         } else { //check length and the content
        	 if(input.startsWith("<![CDATA[") && input.endsWith("]]>") ) {
        		 validateInput(input.substring(9, input.length()-3));
        		 return;
        	 }
        	 if(input.startsWith("<?xml")) {
        		 this.assertValidXmlText(input, "Attempt to create an illegal RoleValue xml input: " + input);
        	 } else if(input.startsWith("{") && input.endsWith("}") ||
        			 input.startsWith("[") && input.endsWith("]") ) {
        		 this.assertArgumentWithSafeJsonInput(input, minLength, maxLength, "Attempt to create an illegal RoleValue json input: " + input);
        	 } else if(input.startsWith("<html>") && input.endsWith("</html>")) {
                 this.assertValidHtmlText(input, Safelist.relaxed(), "Attempt to create an illegal RoleValue html input: " + input);
        	 } else {
        		 this.assertArgumentWithSafeInput(input, minLength, maxLength, "Attempt to create an illegal input: "  + input);
        	 }
    	 }
    }

    
    @Override
    public String[] getWhiteList() {
    	return new String[]{"notset", "not set", "unknown", "n/a"};
    }
   
}

