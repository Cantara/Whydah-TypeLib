package net.whydah.sso.application.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:erik-dev@fjas.no">Erik Drolshammer</a> 2015-07-01
 */
public class ApplicationSecurity implements Serializable {
    private static final long serialVersionUID = -1064310396610968939L;

    /**
     * The minimum security level for user and application tokens allowed to use application.
     * Legal security level values are: 0, 1, 2, 3, 4, 5
     * Default 0 - minimum security level is 0.  .
     */
    private String minSecurityLevel;
    /**
     * The minimum DEFCON level an application accepts to have session under.
     * Defaults to DEFCON5 - all clear sessions only
     */
    private String minDEFCON;
    /**
     * max length of a application and user session for a given application
     *
     * Defaults to 60*60*24 = 24 hours
     */
    private String maxSessionTimeoutSeconds;
    /**
     * The ip addresses/ip ranges we accept an application to send requests from to this application.
     *
     * Defalts to no check setting
     *
     */
    private List<String> allowedIpAddresses;

    /**
     *  default true, false will send userTokens with roles for all applications and is reserved for
     *  coordinnating and administrating applications
     *  
     */
    private String userTokenFilter; //


    //authentication info
    private String secret;    // TODO extend


    public ApplicationSecurity() {
        this.minSecurityLevel = "0";
        this.minDEFCON = "DEFCON5";
        this.maxSessionTimeoutSeconds = (60 * 60 * 24) + "";
        this.allowedIpAddresses = new ArrayList<>();
        allowedIpAddresses.add("0.0.0.0/0");
        this.userTokenFilter = "true";
    }

    public String getMinSecurityLevel() {
        return minSecurityLevel;
    }

    public void setMinSecurityLevel(String minSecurityLevel) {
        this.minSecurityLevel = minSecurityLevel;
    }

    public String getMinDEFCON() {
        return minDEFCON;
    }

    public void setMinDEFCON(String minDEFCON) {
        this.minDEFCON = minDEFCON;
    }

    public String getMaxSessionTimeoutSeconds() {
        return maxSessionTimeoutSeconds;
    }

    public void setMaxSessionTimeoutSeconds(String maxSessionTimeoutSeconds) {
        this.maxSessionTimeoutSeconds = maxSessionTimeoutSeconds;
    }

    public List<String> getAllowedIpAddresses() {
        return allowedIpAddresses;
    }

    public void setAllowedIpAddresses(List<String> allowedIpAddresses) {
        this.allowedIpAddresses = allowedIpAddresses;
    }

    public String getUserTokenFilter() {
        return userTokenFilter;
    }

    public void setUserTokenFilter(String userTokenFilter) {
        this.userTokenFilter = userTokenFilter;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationSecurity that = (ApplicationSecurity) o;

        if (allowedIpAddresses != null ? !allowedIpAddresses.equals(that.allowedIpAddresses) : that.allowedIpAddresses != null)
            return false;
        if (maxSessionTimeoutSeconds != null ? !maxSessionTimeoutSeconds.equals(that.maxSessionTimeoutSeconds) : that.maxSessionTimeoutSeconds != null)
            return false;
        if (minDEFCON != null ? !minDEFCON.equals(that.minDEFCON) : that.minDEFCON != null) return false;
        if (minSecurityLevel != null ? !minSecurityLevel.equals(that.minSecurityLevel) : that.minSecurityLevel != null)
            return false;
        if (secret != null ? !secret.equals(that.secret) : that.secret != null) return false;
        return !(userTokenFilter != null ? !userTokenFilter.equals(that.userTokenFilter) : that.userTokenFilter != null);

    }

    @Override
    public int hashCode() {
        int result = minSecurityLevel != null ? minSecurityLevel.hashCode() : 0;
        result = 31 * result + (minDEFCON != null ? minDEFCON.hashCode() : 0);
        result = 31 * result + (maxSessionTimeoutSeconds != null ? maxSessionTimeoutSeconds.hashCode() : 0);
        result = 31 * result + (allowedIpAddresses != null ? allowedIpAddresses.hashCode() : 0);
        result = 31 * result + (userTokenFilter != null ? userTokenFilter.hashCode() : 0);
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationSecurity{" +
                "minSecurityLevel='" + minSecurityLevel + '\'' +
                ", minDEFCON='" + minDEFCON + '\'' +
                ", maxSessionTimeoutSeconds='" + maxSessionTimeoutSeconds + '\'' +
                ", allowedIpAddresses=" + allowedIpAddresses +
                ", userTokenFilter='" + userTokenFilter + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
