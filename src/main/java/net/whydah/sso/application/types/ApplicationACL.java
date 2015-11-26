package net.whydah.sso.application.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationACL implements Serializable {
    private static final long serialVersionUID = -8050935915438584578L;

    /**
     * The applicationId we grant access to
     *
     */
    private String applicationId;
    /**
     * The URI path we grant access to
     */
    private String applicationACLPath;

    /**
     * A list of granted access rights
     *
     * Valid rights:  "READ", "WRITE", "CREATE", "DELETE"
     */
    private List<String> accessRights;

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";
    private static final String CREATE = "CREATE";
    private static final String DELETE = "DELETE";

    private ApplicationACL() {
    }

    public ApplicationACL(String applicationId, String applicationACLPath) {
        this.applicationId = applicationId;
        this.applicationACLPath = applicationACLPath;
        this.accessRights = new ArrayList<>();
        accessRights.add(ApplicationACL.READ);
    }

    public ApplicationACL(String applicationId, String applicationACLPath,String right) {
        this.applicationId = applicationId;
        this.applicationACLPath = applicationACLPath;
        this.accessRights = new ArrayList<>();
        accessRights.add(right);
    }

    public void setId(String applicationId) {
        this.applicationId = applicationId;
    }
    public void setName(String name) {
        this.applicationACLPath = name;
    }

    public String getApplicationId() {
        return applicationId;
    }
    public String getApplicationACLPath() {
        return applicationACLPath;
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
