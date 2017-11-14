package net.whydah.sso.ddd.model;

import java.io.Serializable;


public abstract class ValueObject extends AssertionConcern implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ValueObject() {
        super();
    }
}
