package net.whydah.sso.application.helpers;

import net.whydah.sso.basehelpers.XpathHelper;
import net.whydah.sso.ddd.model.application.ApplicationId;
import net.whydah.sso.ddd.model.application.ApplicationSecret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApplicationTokenXpathHelper {

    private final static Logger log = LoggerFactory.getLogger(ApplicationTokenXpathHelper.class);


    public static String getApplicationIDFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        String applicationId = new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/*/applicationID[1]");
        if (!ApplicationId.isValid(applicationId)) {
            applicationId = new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/appid[1]");
        }
        log.warn("Old appid fallback for applicationID");

        return applicationId;
    }

    public static String getApplicationNameFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        return new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/*/applicationName[1]");      
    }

    public static String getApplicationSecretFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        String applicationSecret = new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/*/applicationSecret[1]");
        if (!ApplicationSecret.isValid(applicationSecret)) {
            log.warn("Old appsecret fallback for applicationSecret");
            applicationSecret = new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/appsecret[1]");
        }

        return applicationSecret;
    }

    public static String getApplicationTokenIDFromApplicationToken(String applicationTokenXML) {
        log.debug("applicationTokenXML: {}", applicationTokenXML);
        return new XpathHelper(applicationTokenXML).findNullableValue("/applicationtoken/*/applicationtokenID[1]");
    }

    public static String getApplicationIDFromApplicationToken(String applicationTokenXML) {
        log.debug("applicationTokenXML: {}", applicationTokenXML);
        return new XpathHelper(applicationTokenXML).findNullableValue("/applicationtoken/*/applicationid[1]");
    }

    public static String getApplicationNameFromApplicationToken(String applicationTokenXML) {
        log.debug("applicationTokenXML: {}", applicationTokenXML);
        return new XpathHelper(applicationTokenXML).findNullableValue("/applicationtoken/*/applicationname[1]");
    }

    public static String getApplicationExpiresFromApplicationToken(String applicationTokenXML) {
        log.debug("applicationTokenXML: {}", applicationTokenXML);
        return new XpathHelper(applicationTokenXML).findNullableValue("/applicationtoken/*/expires[1]");

    }

    public static String getApplicationTagsFromApplicationToken(String applicationTokenXML) {
        log.debug("applicationTokenXML: {}", applicationTokenXML);
        return new XpathHelper(applicationTokenXML).findNullableValue("/applicationtoken/*/applicationtags[1]");

    }
}
