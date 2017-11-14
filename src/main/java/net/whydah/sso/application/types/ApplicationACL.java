package net.whydah.sso.application.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.whydah.sso.ddd.model.ApplicationId;
import net.whydah.sso.ddd.model.ApplicationPath;

public class ApplicationACL implements Serializable {
    private static final long serialVersionUID = -8050935915438584578L;

    /**
     * The applicationId we grant access to
     *
     */
    private ApplicationId applicationId=new ApplicationId("Not set");
    /**
     * The URI path we grant access to
     */
    private ApplicationPath applicationACLPath=new ApplicationPath("");

    /**
     * A list of granted access rights
     *
     * Valid rights:  "READ", "WRITE", "CREATE", "DELETE", "OAUTH2_REDIRECT"
     */
    private List<String> accessRights;

    public static final String READ = "READ";
    public static final String WRITE = "WRITE";
    public static final String CREATE = "CREATE";
    public static final String DELETE = "DELETE";
    public static final String OAUTH2_REDIRECT = "OAUTH2_REDIRECT";

    private ApplicationACL() {
    }

    public ApplicationACL(String applicationId, String applicationACLPath) {
        this.applicationId = new ApplicationId(applicationId);
        this.applicationACLPath = new ApplicationPath(applicationACLPath);
        this.accessRights = new ArrayList<>();
        accessRights.add(ApplicationACL.READ);
    }

    public ApplicationACL(String applicationId, String applicationACLPath,String right) {
        this.applicationId = new ApplicationId(applicationId);
        this.applicationACLPath = new ApplicationPath(applicationACLPath);
        this.accessRights = new ArrayList<>();
        accessRights.add(right);
    }

    public void setId(String applicationId) {
    	 this.applicationId = new ApplicationId(applicationId);
    }
    public void setName(String name) {
    	this.applicationACLPath = new ApplicationPath(name);
    }

    public String getApplicationId() {
        return applicationId.getId();
    }
    public String getApplicationACLPath() {
        return applicationACLPath.getInput();
    }

    public List<String> getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(List<String> accessRights) {
        this.accessRights = accessRights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationACL that = (ApplicationACL) o;

        if (!applicationId.equals(that.applicationId)) return false;
        if (!applicationACLPath.equals(that.applicationACLPath)) return false;
        return !(accessRights != null ? !accessRights.equals(that.accessRights) : that.accessRights != null);

    }

    @Override
    public int hashCode() {
        int result = applicationId.hashCode();
        result = 31 * result + applicationACLPath.hashCode();
        result = 31 * result + (accessRights != null ? accessRights.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationACL{" +
                "applicationId='" + applicationId + '\'' +
                ", applicationACLPath='" + applicationACLPath + '\'' +
                ", accessRights='" + accessRights + '\'' +
                '}';
    }

}
