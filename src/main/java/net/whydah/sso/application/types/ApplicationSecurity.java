package net.whydah.sso.application.types;

import net.whydah.sso.ddd.model.application.ApplicationSecret;
import net.whydah.sso.ddd.model.base.BaseExpires;
import net.whydah.sso.ddd.model.sso.UserTokenLifespan;
import net.whydah.sso.ddd.model.user.SecurityLevel;
import net.whydah.sso.whydah.DEFCON;

import java.io.Serializable;
import java.util.*;

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
    private SecurityLevel minSecurityLevel;
    /**
     * The minimum DEFCON level an application accepts to have session under.
     * Defaults to DEFCON5 - all clear sessions only
     */
    private DEFCON minimumDEFCONLevel;
    /**
     * max length of a application and user session for a given application
     *
     * Defaults to 60*60*24 = 24 hours
     */
    private UserTokenLifespan maxSessionTimeoutSeconds = new UserTokenLifespan(60 * 60 * 24 * 1000);
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
    private boolean userTokenFilter; //


    private boolean whydahUASAccess=false; //
    private boolean isWhydahAdmin=false;


    //authentication info
    private ApplicationSecret secret=new ApplicationSecret(UUID.randomUUID().toString());


    public ApplicationSecurity() {
        this.minSecurityLevel = new SecurityLevel(0);
        this.minimumDEFCONLevel = DEFCON.DEFCON5;
        this.maxSessionTimeoutSeconds = new UserTokenLifespan(BaseExpires.addPeriod(Calendar.MONTH, 6));  // 6 months
        this.allowedIpAddresses = new ArrayList<>();
        allowedIpAddresses.add("0.0.0.0/0");
        this.userTokenFilter = true;
        this.setWhydahUASAccess(false);
        this.setWhydahAdmin(false);
    }

    public int getMinSecurityLevel() {
        return minSecurityLevel.getLevel();
    }

    public void setMinSecurityLevel(String minSecurityLevel) {
        this.minSecurityLevel = new SecurityLevel(minSecurityLevel);
    }

    public DEFCON getMinimumDEFCONLevel() {
        return minimumDEFCONLevel;
    }

    public void setMinimumDEFCONLevel(String minimumDEFCONLevel) {
        this.minimumDEFCONLevel = DEFCON.valueOf(minimumDEFCONLevel);
    }



    public Long getMaxSessionTimeoutSeconds() {
        return maxSessionTimeoutSeconds.getTimeoutInterval();
    }


    public void setMaxSessionTimeoutSeconds(String maxSessionTimeoutSeconds) {
        this.maxSessionTimeoutSeconds = new UserTokenLifespan(maxSessionTimeoutSeconds);
    }

    public List<String> getAllowedIpAddresses() {
        return allowedIpAddresses;
    }

    public void setAllowedIpAddresses(List<String> allowedIpAddresses) {
        this.allowedIpAddresses = allowedIpAddresses;
    }

    public boolean getUserTokenFilter() {
        return userTokenFilter;
    }

    public void setUserTokenFilter(String userTokenFilter) {
        this.userTokenFilter = Boolean.valueOf(userTokenFilter);
    }

    public String getSecret() {
        return secret.getInput();
    }

    public void setSecret(String secret) {
        if (secret != null) {
            this.secret = new ApplicationSecret(secret);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationSecurity that = (ApplicationSecurity) o;

        if (!Objects.equals(minSecurityLevel, that.minSecurityLevel)) return false;
        if (!Objects.equals(maxSessionTimeoutSeconds,that.maxSessionTimeoutSeconds)) return false;
        if (!Objects.equals(userTokenFilter,that.userTokenFilter)) return false;
        if (!Objects.equals(whydahUASAccess, that.whydahUASAccess)) return false;
        if (!Objects.equals(isWhydahAdmin, that.isWhydahAdmin)) return false;
        if (!Objects.equals(minimumDEFCONLevel, that.minimumDEFCONLevel)) return false;
        if (allowedIpAddresses != null ? !allowedIpAddresses.equals(that.allowedIpAddresses) : that.allowedIpAddresses != null)
            return false;
        return secret != null ? secret.equals(that.secret) : that.secret == null;
    }
    
    public boolean isWhydahUASAccess() {
		return whydahUASAccess;
	}

	public void setWhydahUASAccess(boolean whydahUASAccess) {
		this.whydahUASAccess = whydahUASAccess;
	}

    @Override
    public int hashCode() {
        int result = minSecurityLevel.getLevel();
        result = 31 * result + (minimumDEFCONLevel != null ? minimumDEFCONLevel.hashCode() : 0);
        result = 31 * result + (int) (getMaxSessionTimeoutSeconds() ^ (getMaxSessionTimeoutSeconds() >>> 32));
        result = 31 * result + (allowedIpAddresses != null ? allowedIpAddresses.hashCode() : 0);
        result = 31 * result + (userTokenFilter ? 1 : 0);
        result = 31 * result + (whydahUASAccess ? 1 : 0);
        result = 31 * result + (isWhydahAdmin ? 1 :0);
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationSecurity{" +
                "minSecurityLevel='" + minSecurityLevel + '\'' +
                ", minimumDEFCONLevel='" + minimumDEFCONLevel + '\'' +
                ", maxSessionTimeoutSeconds='" + maxSessionTimeoutSeconds + '\'' +
                ", allowedIpAddresses=" + allowedIpAddresses +
                ", userTokenFilter=" + userTokenFilter +
                ", whydahUASAccess=" + isWhydahUASAccess() +
                ", isWhydahAdmin=" + isWhydahAdmin() +
                ", secret='******" + +'\'' +
                '}';
    }

	public boolean isWhydahAdmin() {
		return isWhydahAdmin;
	}

	public void setWhydahAdmin(boolean isWhydahAdmin) {
		this.isWhydahAdmin = isWhydahAdmin;
	}

	
}
