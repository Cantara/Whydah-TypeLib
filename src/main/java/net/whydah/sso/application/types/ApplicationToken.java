package net.whydah.sso.application.types;

import net.whydah.sso.application.mappers.ApplicationTagMapper;
import net.whydah.sso.ddd.model.application.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


public class ApplicationToken implements Serializable {
    private final static Logger log = LoggerFactory.getLogger(ApplicationToken.class);


    private ApplicationTokenID applicationTokenId = new ApplicationTokenID(UUID.randomUUID().toString());
    private ApplicationSecret applicationSecret=new ApplicationSecret(UUID.randomUUID().toString());
    private ApplicationName applicationName = new ApplicationName("");
    private ApplicationId applicationID = new ApplicationId(null);
    private List<Tag> tags = new LinkedList<>();
    private ApplicationTokenExpires expires = new ApplicationTokenExpires((System.currentTimeMillis() + 1000));
    private ApplicationUrl baseuri = new ApplicationUrl("");

    public void ApplicationToken() {
    }

    public String getBaseuri() {
        return baseuri!=null?baseuri.getInput():null;
    }

    public void setBaseuri(String baseuri) {
        this.baseuri = new ApplicationUrl(baseuri);
    }

    private boolean template = true;


    public String getApplicationTokenId() {
        return applicationTokenId!=null?applicationTokenId.getId():null;
    }


    public String getExpires() {
        return expires!=null? Long.toString(expires.getMillisecondValue(), 10):null;
    }

    public String getExpiresFormatted() {
        return expires!=null?expires.getDateFormatted():null;
    }

    public void setExpires(String expires) {
        this.expires = new ApplicationTokenExpires(expires);
    }

    public String getApplicationName() {
        return applicationName!=null?applicationName.getInput():null;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = new ApplicationName(applicationName);
    }

    public String getApplicationID() {
        return applicationID!=null?applicationID.getId():null;
    }

    public void setApplicationTokenId(String applicationTokenId) {
        this.applicationTokenId = new ApplicationTokenID(applicationTokenId);
    }

    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = new ApplicationSecret(applicationSecret);
    }
    

    public String getApplicationSecret() {
        return this.applicationSecret!=null?applicationSecret.getInput():null;
    }


    public void setApplicationID(String applicationID) {

        this.applicationID = new ApplicationId(applicationID);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags.addAll(tags);
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
        return getMD5hash(appID + Long.toString(expires.getMillisecondValue(), 10));
    }

    @Override
    public String toString() {
        return "ApplicationToken{" +
                "applicationTokenId='" + getApplicationTokenId() + '\'' +
                ", applicationSecret='" + getApplicationSecret() + '\'' +
                ", applicationName='" + getApplicationName() + '\'' +
                ", applicationID='" + getApplicationID() + '\'' +
                ", tags='" + ApplicationTagMapper.toJson(getTags()) + '\'' +
                ", expires='" + getExpires() + '\'' +
                ", baseuri='" + getBaseuri() + '\'' +
                ", template=" + template +
                '}';
    }
}

