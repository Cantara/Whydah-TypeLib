package net.whydah.sso.ddd.model.base;

import net.whydah.sso.basehelpers.Validator;

public class AbstractBaseId extends AbstractId {

    public AbstractBaseId(String id) {
    	super(id);
    }
    
    public AbstractBaseId(String id, int minLength, int maxLength) {
    	super(id, minLength, maxLength);
    }
    
    @Override
    protected void validateInput(String anId) {
    	super.validateInput(anId);

        assertArgumentWithAPattern(anId, Validator.DEFAULT_BASE_ID, "Attempt to create an illegal public class AbstractBaseId extends AbstractId {\n - illegal characters: " + anId);

    }
}