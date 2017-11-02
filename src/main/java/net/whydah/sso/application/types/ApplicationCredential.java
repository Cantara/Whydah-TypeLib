package net.whydah.sso.application.types;

import net.whydah.sso.basehelpers.ValidationConfig;
import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.WhydahIdentity;
import net.whydah.sso.ddd.WhydahName;
import net.whydah.sso.whydah.DEFCON;

import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import static net.whydah.sso.basehelpers.Sanitizers.sanitize;

public class ApplicationCredential {
    private WhydahIdentity applicationID = new WhydahIdentity("Uninitialized");
    private WhydahName applicationName = new WhydahName("");
    private final String applicationSecret;
    private final String applicationurl;
    private final String minimumsecuritylevel;
    private final String minimumDEFCONlevel;
    private final static Logger log = LoggerFactory.getLogger(ApplicationCredential.class);


    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret, String applicationurl, String securitylevel) {
        setApplicationID(applicationID);
        this.applicationName = new WhydahName(applicationName);
        this.applicationSecret = applicationSecret;
        this.applicationurl = applicationurl;
        this.minimumsecuritylevel = securitylevel;
        this.minimumDEFCONlevel = DEFCON.DEFCON5.toString();
    }

    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret) {
        setApplicationID(applicationID);
        this.applicationName = new WhydahName(applicationName);
        this.applicationSecret = applicationSecret;
        this.applicationurl = "";
        this.minimumsecuritylevel = "0";
        this.minimumDEFCONlevel = DEFCON.DEFCON5.toString();
    }

    private void setApplicationID(String someUUID) {
        this.applicationID = new WhydahIdentity(someUUID);
    }

    public String getApplicationID() {
        return applicationID.getId();
    }

    public String getApplicationName() {
        //return sanitize(applicationName.getName());
    	return Validator.sanitize(applicationName.getName());
    }

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public String getApplicationurl() {
    	 boolean isValidUrl = Validator.isValidTextInput(applicationurl, ValidationConfig.DEFAULT_MIN_LENGTH, ValidationConfig.DEFAULT_MAX_LENGTH, Validator.DEFAULT_URL_PATTERN);
        return applicationurl;
    }

    public String getMinimumsecuritylevel() {
        return minimumsecuritylevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationCredential that = (ApplicationCredential) o;

        if (getApplicationID() != null ? !getApplicationID().equals(that.getApplicationID()) : that.getApplicationID() != null)
            return false;
        if (getApplicationName() != null ? !getApplicationName().equals(that.getApplicationName()) : that.getApplicationName() != null)
            return false;
        if (applicationSecret != null ? !applicationSecret.equals(that.applicationSecret) : that.applicationSecret != null)
            return false;
        if (applicationurl != null ? !applicationurl.equals(that.applicationurl) : that.applicationurl != null)
            return false;
        if (minimumsecuritylevel != null ? !minimumsecuritylevel.equals(that.minimumsecuritylevel) : that.minimumsecuritylevel != null)
            return false;
        return minimumDEFCONlevel != null ? minimumDEFCONlevel.equals(that.minimumDEFCONlevel) : that.minimumDEFCONlevel == null;
    }

    @Override
    public int hashCode() {
        int result = getApplicationID() != null ? getApplicationID().hashCode() : 0;
        result = 31 * result + (getApplicationName() != null ? getApplicationName().hashCode() : 0);
        result = 31 * result + (applicationSecret != null ? applicationSecret.hashCode() : 0);
        result = 31 * result + (applicationurl != null ? applicationurl.hashCode() : 0);
        result = 31 * result + (minimumsecuritylevel != null ? minimumsecuritylevel.hashCode() : 0);
        result = 31 * result + (minimumDEFCONlevel != null ? minimumDEFCONlevel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationCredential{" +
                "applicationID='" + getApplicationID() + '\'' +
                ", applicationName='" + getApplicationName() + '\'' +
                ", applicationSecret='" + "******" + '\'' +
                ", applicationurl='" + applicationurl + '\'' +
                ", minimumsecuritylevel='" + minimumsecuritylevel + '\'' +
                ", minimumDEFCONlevel='" + minimumDEFCONlevel + '\'' +
                '}';
    }
}
