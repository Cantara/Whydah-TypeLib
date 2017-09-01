package net.whydah.sso.application.types;

import net.whydah.sso.whydah.DEFCON;

public class ApplicationCredential {
    private final String applicationID;
    private final String applicationName;
    private final String applicationSecret;
    private final String applicationurl;
    private final String minimumsecuritylevel;
    private final String minimumDEFCONlevel;


    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret, String applicationurl, String securitylevel) {
        this.applicationID = applicationID;
        this.applicationName = applicationName;
        this.applicationSecret = applicationSecret;
        this.applicationurl = applicationurl;
        this.minimumsecuritylevel = securitylevel;
        this.minimumDEFCONlevel = DEFCON.DEFCON5.toString();
    }

    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret) {
        this.applicationID = applicationID;
        this.applicationName = applicationName;
        this.applicationSecret = applicationSecret;
        this.applicationurl = "";
        this.minimumsecuritylevel = "0";
        this.minimumDEFCONlevel = DEFCON.DEFCON5.toString();
    }

    public String getApplicationID() {
        return applicationID;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public String getApplicationurl() {
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

        if (applicationID != null ? !applicationID.equals(that.applicationID) : that.applicationID != null)
            return false;
        if (applicationName != null ? !applicationName.equals(that.applicationName) : that.applicationName != null)
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
        int result = applicationID != null ? applicationID.hashCode() : 0;
        result = 31 * result + (applicationName != null ? applicationName.hashCode() : 0);
        result = 31 * result + (applicationSecret != null ? applicationSecret.hashCode() : 0);
        result = 31 * result + (applicationurl != null ? applicationurl.hashCode() : 0);
        result = 31 * result + (minimumsecuritylevel != null ? minimumsecuritylevel.hashCode() : 0);
        result = 31 * result + (minimumDEFCONlevel != null ? minimumDEFCONlevel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationCredential{" +
                "applicationID='" + applicationID + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", applicationSecret='" + "******" + '\'' +
                ", applicationurl='" + applicationurl + '\'' +
                ", minimumsecuritylevel='" + minimumsecuritylevel + '\'' +
                ", minimumDEFCONlevel='" + minimumDEFCONlevel + '\'' +
                '}';
    }
}
