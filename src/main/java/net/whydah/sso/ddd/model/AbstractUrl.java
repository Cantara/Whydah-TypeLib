package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.ValidationConfig;
import net.whydah.sso.basehelpers.Validator;

import java.util.Objects;

public class AbstractUrl extends ValueObject {

	private static final long serialVersionUID = 1L;

	protected final String _input;
	protected int maxLength = ValidationConfig.DEFAULT_MAX_LENGTH_10240;
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
		super();
		this.containsPathsOnly = containsPathsOnly;
		if(input!=null&&!input.equalsIgnoreCase("null")){
			if(!input.equals("") || minLength!=0){
                this.validateInput(input.trim());
            }
		}
        this._input = input;
    }


	public AbstractUrl(String input) {
		this(input, false);
	}


    public boolean isValid() {
        try {
            validateInput(_input);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    protected void validateInput(String input){
		this.assertArgumentLength(input, minLength, maxLength, "The input's length must be "  + String.valueOf(minLength)  + "-" + String.valueOf(maxLength) + ".");

		if(input.length()>0){
			String pathToCheck = containsPathsOnly? (input.startsWith("/")?("http://test.com" + input):("http://testurl.com/" +input)):input;
			boolean isLocal = Validator.isValidTextInput(pathToCheck, minLength, maxLength, Validator.DEFAULT_LOCAL_HOST_URL_PATTERN);
			if(!isLocal){
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

}