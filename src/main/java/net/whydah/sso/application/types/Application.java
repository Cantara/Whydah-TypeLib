package net.whydah.sso.application.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Type for Application.
 *
 * @author <a href="mailto:erik-dev@fjas.no">Erik Drolshammer</a> 2015-06-30
*/
public class Application implements Serializable {
    private static final long serialVersionUID = -3045715282910406580L;
    private String id;
    private String name;            // |/sso/welcome and applicationToken
    private String description;     // /sso/welcome
    private String company;         // Company who own the application
    private String tags;            // A comma-separated string of tags for organizing and convenience
    private String applicationUrl;  // /sso/welcome
    private String logoUrl;         // /sso/welcome


    private boolean fullTokenApplication = false;

    //list ApplicationAvailableRoleNames
    private List<ApplicationAvailableRoleNames> roles;   //availableRoleNames - convenience list of predefined rolenames
    private String defaultRoleName;     //roleName - the default rolename assigned upon new (UserApplicationRoleEntry) access to the application

    //list ApplicationAvailableOrganizationNames
    private List<ApplicationAvailableOrganizationNames> orgs;   //availableRoleNames - convenience list of predefined rolenames
    private String defaultOrganizationName; // - the default organizationName  assigned upon new (UserApplicationRoleEntry) access to the application

    // Application security config
    private ApplicationSecurity security; // The security config for the application
    private List<ApplicationACL> acls;  // List of granted ACL for the application


    private Application() {
    }

    public Application(String id, String name) {
        this.id = id;
        this.name = name;
        this.roles = new ArrayList<>();
        this.orgs = new ArrayList<>();
        this.security = new ApplicationSecurity();
        this.acls = new ArrayList<>();
    }

    public List<ApplicationACL> getAcl() {
        return acls;
    }

    public void setAcl(List<ApplicationACL> acls) {
        this.acls = acls;
    }

    public void addAcl(ApplicationACL acl) {
        acls.add(acl);
    }

    public void addRole(ApplicationAvailableRoleNames role) {
        roles.add(role);
    }

    public void addOrganizationName(ApplicationAvailableOrganizationNames organizationName) {
        orgs.add(organizationName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<ApplicationAvailableRoleNames> getRoles() {
        return roles;
    }

    public void setRoles(List<ApplicationAvailableRoleNames> roles) {
        this.roles = roles;
    }

    public List<ApplicationAvailableOrganizationNames> getOrganizationNames() {
        return orgs;
    }

    public void setOrganizationNames(List<ApplicationAvailableOrganizationNames> orgs) {
        this.orgs = orgs;
    }

    public String getDefaultRoleName() {
        return defaultRoleName;
    }

    public void setDefaultRoleName(String defaultRoleName) {
        this.defaultRoleName = defaultRoleName;
    }

    public String getDefaultOrganizationName() {
        return defaultOrganizationName;
    }

    public void setDefaultOrganizationName(String defaultOrganizationName) {
        this.defaultOrganizationName = defaultOrganizationName;
    }

    public ApplicationSecurity getSecurity() {
        return security;
    }

    public void setSecurity(ApplicationSecurity security) {
        this.security = security;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFullTokenApplication() {
        return String.valueOf(fullTokenApplication);
    }

    public void setFullTokenApplication(String fulltokenValue) {
        this.fullTokenApplication = Boolean.parseBoolean(fulltokenValue);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (applicationUrl != null ? !applicationUrl.equals(that.applicationUrl) : that.applicationUrl != null)
            return false;
        if (defaultOrganizationName != null ? !defaultOrganizationName.equals(that.defaultOrganizationName) : that.defaultOrganizationName != null)
            return false;
        if (defaultRoleName != null ? !defaultRoleName.equals(that.defaultRoleName) : that.defaultRoleName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (logoUrl != null ? !logoUrl.equals(that.logoUrl) : that.logoUrl != null) return false;
        if (!name.equals(that.name)) return false;
        if (orgs != null ? !orgs.equals(that.orgs) : that.orgs != null)
            return false;
        return !(roles != null ? !roles.equals(that.roles) : that.roles != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? company.hashCode() : 0);
        result = 31 * result + (company != null ? description.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (applicationUrl != null ? applicationUrl.hashCode() : 0);
        result = 31 * result + (logoUrl != null ? logoUrl.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (orgs != null ? orgs.hashCode() : 0);
        result = 31 * result + (defaultRoleName != null ? defaultRoleName.hashCode() : 0);
        result = 31 * result + (defaultOrganizationName != null ? defaultOrganizationName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String availableOrgNamesString = "";
        if (orgs != null) {
            StringBuilder strb = new StringBuilder();
            for (ApplicationAvailableOrganizationNames org : orgs) {
                strb.append(org.getName()).append(",");
            }
            availableOrgNamesString = strb.toString();
        }

        String roleNamesString = null;
        if (roles != null) {
            StringBuilder strb = new StringBuilder();
            for (ApplicationAvailableRoleNames role : roles) {
                strb.append(role.getName()).append(",");
            }
            roleNamesString = strb.toString();
        }

        return "Application{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company='" + company + '\'' +
                ", tags='" + tags + '\'' +
                ", applicationUrl='" + applicationUrl + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", roles=" + roleNamesString +
                ", defaultRoleName='" + defaultRoleName + '\'' +
                ", organizationNames=" + availableOrgNamesString +
                ", defaultOrganizationName='" + defaultOrganizationName + '\'' +
                ", '" + security.toString() + '\'' +
                '}';
    }
}
