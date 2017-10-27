package net.whydah.sso.application.types;

import net.whydah.sso.ddd.WhydahIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationTokenID extends WhydahIdentity {

    private final static Logger log = LoggerFactory.getLogger(ApplicationTokenID.class);

    public ApplicationTokenID(String applicationTokenId) {
        super(applicationTokenId);
    }


    @Override
    public String toString() {
        return getId();
    }


}
