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
