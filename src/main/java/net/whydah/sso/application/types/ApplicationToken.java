package net.whydah.sso.application.types;

import net.whydah.sso.ddd.WhydahIdentity;
import net.whydah.sso.ddd.application.ApplicationTokenExpires;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.UUID;


public class ApplicationToken implements Serializable {
    private final static Logger log = LoggerFactory.getLogger(ApplicationToken.class);


    private ApplicationTokenID applicationTokenId = new ApplicationTokenID(UUID.randomUUID().toString());
    private String applicationSecret;
    private String applicationName;
    private WhydahIdentity applicationID = new WhydahIdentity("");
    private ApplicationTokenExpires expires = new ApplicationTokenExpires((System.currentTimeMillis() + 1000));
    private String baseuri = "http://example.com//";

    public void ApplicationToken() {
    }

    public String getBaseuri() {
        return baseuri;
    }

    public void setBaseuri(String baseuri) {
        this.baseuri = baseuri;
    }

    private boolean template = true;


    public String getApplicationTokenId() {
        return applicationTokenId.getId();
    }


    public String getExpires() {
        return Long.toString(expires.getMillisecondValue(), 10);
    }

    public String getExpiresFormatted() {
        return expires.getDateFormatted();
    }

    public void setExpires(String expires) {
        this.expires = new ApplicationTokenExpires(Long.parseLong(expires));
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationID() {
        return applicationID.getId();
    }

    public void setApplicationTokenId(String applicationTokenId) {
        this.applicationTokenId = new ApplicationTokenID(applicationTokenId);
    }

    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = applicationSecret;
    }

    public void setApplicationID(String applicationID) {

        this.applicationID = new WhydahIdentity(applicationID);
    }


    public String getMD5hash(String t) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(t.getBytes());
            byte[] h = digest.digest();
            return getHexString(h);
        } catch (Exception e) {
            log.error("Could not get MD5 hash for string " + t, e);
        }
        return "";
    }

    public String getHexString(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (byte aB : b) {
            result.append(Integer.toString((aB & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    private String getApplicationTokenFromApplicationCredential(String appID) {
        return getMD5hash(appID + expires);
    }

    @Override
    public String toString() {
        return "ApplicationToken{" +
                "applicationTokenId='" + applicationTokenId.getId() + '\'' +
                ", applicationSecret='" + applicationSecret + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", applicationID='" + applicationID.getId() + '\'' +
                ", expires='" + getExpires() + '\'' +
                ", baseuri='" + baseuri + '\'' +
                ", template=" + template +
                '}';
    }
}

