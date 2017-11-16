package net.whydah.sso.ddd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public abstract class ValueObject extends AssertionConcern implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected final String _input;

	public ValueObject(String input) {
		this._input = input;
	}

	public String getInput(){
		return _input;
	}

	public abstract String[] getWhiteList();

	protected boolean isWhiteListed(String value){
		if(value==null || value.equals("null")){
			return true;
		}
		if(getWhiteList()!=null){
			WhiteListArrayList white = new WhiteListArrayList();
			white.addAll(Arrays.asList(getWhiteList()));
			return white.contains(value);
		}
		return false;
	}

	@Override
	public String toString() {
		return String.valueOf(_input);
	}
	
	public class WhiteListArrayList extends ArrayList<String> {
	    @Override
	    public boolean contains(Object o) {
	        String paramStr = (String)o;
	        for (String s : this) {
	            if (paramStr.equalsIgnoreCase(s)) return true;
	        }
	        return false;
	    }
	}
}
