package net.whydah.sso.application.helpers;

import net.whydah.sso.basehelpers.XpathHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApplicationTokenXpathHelper {

    private final static Logger log = LoggerFactory.getLogger(ApplicationTokenXpathHelper.class);


    public static String getApplicationIDFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        return new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/*/applicationID[1]");
    }

    public static String getApplicationNameFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        return new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/*/applicationName[1]");      
    }

    public static String getApplicationSecretFromApplicationCredential(String applicationCredentialXML) {
        log.debug("applicationCredentialXML: {}", applicationCredentialXML);
        return new XpathHelper(applicationCredentialXML).findNullableValue("/applicationcredential/*/applicationSecret[1]");      
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
}
