package net.whydah.sso.ddd.model.base;

import java.util.Objects;

public class AbstractId extends ValueObject {

	private static final long serialVersionUID = 1L;

	
    protected int _minLength = 0;
    protected int _maxLength=36;

	public String getId() {
		return this._input;
	}


	public AbstractId(String anId) {
		this(anId, 3, 36);
	}

	public AbstractId(String anId, int minLength, int maxLength) {
		super(anId);
		if(isWhiteListed(anId)){
			return;
		}
		this._minLength = minLength;
		this._maxLength = maxLength;
		if(anId!=null && !anId.equalsIgnoreCase("null")){
			if(!anId.equals("") || _minLength!=0){
				this.validateInput(anId);
			}
		}
		
	}

	protected void validateInput(String anId){

        this.assertArgumentLength(anId, _minLength, _maxLength, "The basic identity must have " + String.valueOf(_minLength) + "-" + String.valueOf(_maxLength) + " characters. Value: " + anId);
    }

  

	@Override
	public int hashCode() {
		return Objects.hash(_input);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof AbstractId)) {
			return false;
		}
		AbstractId n = (AbstractId) o;
		return Objects.equals(n.getId(), _input);
	}


	@Override
	public String[] getWhiteList() {
		return new String[]{"not set", "notset"};
	}

}