package net.whydah.sso.application.types;

import net.whydah.sso.whydah.DEFCON;

public class ApplicationCredential {
    private final String applicationID;
    private final String applicationName;
    private final String applicationSecret;
    private final String applicationurl;
    private final String minimumsecuritylevel;


    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret, String applicationurl, String minimumsecuritylevel) {
        this.applicationID = applicationID;
        this.applicationName = applicationName;
        this.applicationSecret = applicationSecret;
        this.applicationurl = applicationurl;
        this.minimumsecuritylevel = minimumsecuritylevel;
    }

    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret) {
        this.applicationID = applicationID;
        this.applicationName = applicationName;
        this.applicationSecret = applicationSecret;
        this.applicationurl = "";
        this.minimumsecuritylevel = DEFCON.DEFCON5.toString();
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
}
