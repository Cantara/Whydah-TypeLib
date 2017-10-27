package net.whydah.sso.ddd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhydahReference extends WhydahIdentity {

    private final static Logger log = LoggerFactory.getLogger(WhydahReference.class);

    // Same rules applies as for Identities
    public WhydahReference(String whydahReference) {
        super(whydahReference);
    }
}
