package net.whydah.sso.application.types;

public class ApplicationCredential {
    private final String applicationID;
    private final String applicationName;
    private final String applicationSecret;


    public ApplicationCredential(String applicationID, String applicationName, String applicationSecret) {
        this.applicationID = applicationID;
        this.applicationName = applicationName;
        this.applicationSecret = applicationSecret;
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
}
