package net.whydah.sso.ddd.model;

import java.util.Objects;

import net.whydah.sso.basehelpers.ValidationConfig;
import net.whydah.sso.basehelpers.Validator;

public class AbstractUrl extends ValueObject {

    private static final long serialVersionUID = 1L;

    private final String _input;
    private int maxLength = ValidationConfig.DEFAULT_MAX_LENGTH_10240;
    private int minLength = 0; //allow empty
    private boolean containsPathsOnly=false;
    
    public String getInput() {
        return this._input;
    }


    @Override
    public String toString() {
        return this._input;
    }

    public AbstractUrl(String input, boolean containsPathsOnly) {
        super();
        this.containsPathsOnly = containsPathsOnly;
        if(input!=null&&!input.equalsIgnoreCase("null")){
        	this.validateInput(input);
        }
        this._input = input;
    }

    
    public AbstractUrl(String input) {
       this(input, false);
    }

    protected void validateInput(String input){
         this.assertArgumentLength(input, minLength, maxLength, "The input's length must be "  + String.valueOf(minLength)  + "-" + String.valueOf(maxLength) + ".");
         
         if(input.length()>0){
        	 this.assertArgumentWithAPattern(containsPathsOnly? (input.startsWith("/")?("http://test.com" + input):("http://testurl.com/" +input)):input, Validator.DEFAULT_URL_PATTERN, "The URL is not in a valid format");
         }
    }

    @Override
    public int hashCode() {
    	return Objects.hash(_input);
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o == this) return true;
        if (!(o instanceof AbstractUrl)) {
            return false;
        }
        AbstractUrl n = (AbstractUrl) o;
        return Objects.equals(n.getInput(), _input);
    }
   
}