package net.whydah.sso.ddd.model.base;

import net.whydah.sso.basehelpers.Validator;

import java.util.Objects;

public class AbstractUrl extends ValueObject {

	private static final long serialVersionUID = 1L;

	
	protected int maxLength = Validator.DEFAULT_MAX_LENGTH_10240;
	protected int minLength = 0; //allow empty
	protected boolean containsPathsOnly=false;

	public String getInput() {
		return this._input;
	}


	@Override
	public String toString() {
		return this._input;
	}

	public AbstractUrl(String input, boolean containsPathsOnly) {
		super(input);
		if(isWhiteListed(input)){
			return;
		}
		this.containsPathsOnly = containsPathsOnly;
		if(input!=null&&!input.equalsIgnoreCase("null")){
			if(!input.equals("") || minLength!=0){
                this.validateInput(input.trim());
            }
		}
       
    }


	public AbstractUrl(String input) {
		this(input, false);
	}


    protected void validateInput(String input){
		this.assertArgumentLength(input, minLength, maxLength, "The input's length must be "  + String.valueOf(minLength)  + "-" + String.valueOf(maxLength) + ".");

		if(input.length()>0){
			String pathToCheck = containsPathsOnly? (input.startsWith("/")?("http://test.com" + input):("http://testurl.com/" +input)):input;
			//skip local url
			if(!pathToCheck.startsWith("http://localhost") && !pathToCheck.startsWith("http://127.0.0.1")){
				this.assertArgumentWithAPattern(pathToCheck, Validator.DEFAULT_URL_PATTERN, "The URL " + input+ " is not in a valid format");
			}
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
	
	@Override
	public String[] getWhiteList() {
		return new String[]{"n/a", null, "null"};
	}

}