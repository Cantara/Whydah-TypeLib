package net.whydah.sso.application.types;

import net.whydah.sso.application.mappers.ApplicationCredentialMapper;
import net.whydah.sso.ddd.model.application.ApplicationId;
import net.whydah.sso.ddd.model.application.ApplicationName;
import net.whydah.sso.ddd.model.application.ApplicationSecret;
import net.whydah.sso.ddd.model.application.ApplicationUrl;
import net.whydah.sso.ddd.model.user.SecurityLevel;
import net.whydah.sso.whydah.DEFCON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

//import static net.whydah.sso.basehelpers.Sanitizers.sanitize;

public class ApplicationCredential {
    private ApplicationId applicationID = new ApplicationId("Uninitialized");
    private ApplicationName applicationName = new ApplicationName("Uninitialized");
    private ApplicationSecret applicationSecret = new ApplicationSecret(UUID.randomUUID().toString());
    private ApplicationUrl applicationurl = new ApplicationUrl("");
    private SecurityLevel minimumsecuritylevel = new SecurityLevel(0);
    private DEFCON minimumDEFCONlevel = DEFCON.DEFCON5;
    private final static Logger log = LoggerFactory.getLogger(ApplicationCredential.class);


    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret, String applicationurl, String securitylevel, DEFCON minimumDEFCONLevel) {
        setApplicationID(applicationID);
        this.applicationName = new ApplicationName(applicationName);
        this.applicationSecret = new ApplicationSecret(applicationSecret);
        this.applicationurl = new ApplicationUrl(applicationurl);
        this.minimumsecuritylevel = new SecurityLevel(securitylevel);
        this.minimumDEFCONlevel = minimumDEFCONLevel;
    }
    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret, String applicationurl, String securitylevel) {
        setApplicationID(applicationID);
        this.applicationName = new ApplicationName(applicationName);
        this.applicationSecret = new ApplicationSecret(applicationSecret);
        this.applicationurl = new ApplicationUrl(applicationurl);
        this.minimumsecuritylevel = new SecurityLevel(securitylevel);
        this.minimumDEFCONlevel = DEFCON.DEFCON5;
    }

    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret) {
        setApplicationID(applicationID);
        this.applicationName = new ApplicationName(applicationName);
        this.applicationSecret = new ApplicationSecret(applicationSecret);
        this.applicationurl = new ApplicationUrl(""); //empty, not a null
        this.minimumsecuritylevel = new SecurityLevel(0);
        this.minimumDEFCONlevel = DEFCON.DEFCON5;
    }

    private void setApplicationID(String someUUID) {
        this.applicationID = new ApplicationId(someUUID);
    }

    public String getApplicationID() {
        return applicationID.getId();
    }

    public String getApplicationName() {
        return applicationName.getInput();
    }

    public String getApplicationSecret() {
        return applicationSecret.getInput();
    }

    public String getApplicationurl() {
    	return applicationurl.getInput();
    }

    public String getMinimumsecuritylevel() {
        return minimumsecuritylevel.toString();
    }

    public String getMinimumDEFCONlevel() {
        return minimumDEFCONlevel.toString();
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
        result = 31 * result + (minimumsecuritylevel != null ? minimumsecuritylevel.toString().hashCode() : 0);
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
                ", minimumDEFCONlevel='" + minimumDEFCONlevel.toString() + '\'' +
                '}';
    }

    public static boolean isValid(String applicationCredentialXML) {
        try {
            ApplicationCredential applicationCredential = ApplicationCredentialMapper.fromXml(applicationCredentialXML);
            return applicationCredential.getApplicationID() != null;
        } catch (Exception e) {
        }
        return false;
    }

}
