package net.whydah.sso.ddd.model;

import java.util.Objects;

import net.whydah.sso.basehelpers.ValidationConfig;


public class AbstractName extends ValueObject {

    private static final long serialVersionUID = 1L;

    protected final String _input;
    protected int maxLength = ValidationConfig.DEFAULT_MAX_LENGTH_250;
    protected int minLength = 0; //allow empty
    protected boolean checkSafeInput = true; //check length and content
    
    public String getInput() {
        return this._input;
    }


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
    	this(input, true);
    }
    
    public AbstractName(String input, boolean checkSafeInput) {
    	this(input, 0, ValidationConfig.DEFAULT_MAX_LENGTH_250, true);
       
    }
    
    public AbstractName(String input, int minLength, int maxLength) {
       this(input, minLength, maxLength, true);
    }
    
    public AbstractName(String input, int minLength, int maxLength, boolean checkSafeInput) {
        super();
        this.checkSafeInput = checkSafeInput;
        this.maxLength = maxLength;
        if(input!=null&&!input.equalsIgnoreCase("null")){
        	if(!input.equals("") || minLength!=0){
				this.validateInput(input);
			}
        	
        }
        this._input = input;
        
    }

    protected void validateInput(String input){
    	 if(!checkSafeInput) { //only check length
    		 this.assertArgumentLength(input, minLength, maxLength, "The input's length must be "  + String.valueOf(minLength)  + "-" + String.valueOf(maxLength) + ".");
    	 } else { //check length and the content
    		 this.assertArgumentWithSafeInput(input, minLength, maxLength, "Attempt to create an illegal input: "  + input);
    	 }
    }

   
}

