package net.whydah.sso.application.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.whydah.sso.ddd.model.application.*;
import net.whydah.sso.ddd.model.customer.Tags;
import net.whydah.sso.ddd.model.userrole.OrganizationName;
import net.whydah.sso.whydah.DEFCON;
import net.whydah.sso.whydah.UserSessionSecurityLevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Type for Application.
 *
 * @author <a href="mailto:erik-dev@fjas.no">Erik Drolshammer</a> 2015-06-30
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application implements Serializable {
    private static final long serialVersionUID = -3045715282910406580L;
    private ApplicationId id = new ApplicationId("Not Set");
    private ApplicationName name = new ApplicationName("");            // |/sso/welcome and applicationToken
    private Description description = new Description("");     // /sso/welcome
    private CompanyName company = new CompanyName("");         // Company who own the application
    private Tags tags=new Tags("");            // A comma-separated string of tags for organizing and convenience
    private ApplicationUrl applicationUrl=new ApplicationUrl("");  // /sso/welcome
    private LogoUrl logoUrl = new LogoUrl("");         // /sso/welcome


    //list ApplicationAvailableRoleNames
    private List<ApplicationAvailableRoleNames> roles = new LinkedList<>();   //availableRoleNames - convenience list of predefined rolenames
    private DefaultRoleNames defaultRoleName = new DefaultRoleNames("");     //roleName - the default rolename assigned upon new (UserApplicationRoleEntry) access to the application

    //list ApplicationAvailableOrganizationNames
    private List<ApplicationAvailableOrganizationNames> organizationNames = new LinkedList<>();   //availableRoleNames - convenience list of predefined rolenames
    private OrganizationName defaultOrganizationName = new OrganizationName(""); // - the default organizationName  assigned upon new (UserApplicationRoleEntry) access to the application

    // Application security config
    private ApplicationSecurity security = new ApplicationSecurity(); // The security config for the application

    private List<ApplicationACL> acls = new LinkedList<>();  // List of granted ACL for the application

    private List<String> supportedUserSessionLevels = new LinkedList<String>();

    private Application() {
    }

    public Application(String id, String name) {
        this.id = new ApplicationId(id);
        this.name = new ApplicationName(name);
        this.roles = new ArrayList<>();
        this.organizationNames = new ArrayList<>();
        this.security = new ApplicationSecurity();
        this.acls = new ArrayList<>();
        this.supportedUserSessionLevels = new LinkedList<String>();
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
        organizationNames.add(organizationName);
    }

    public String getId() {
        return id.getId();
    }

    public void setId(String id) {
        this.id = new ApplicationId(id);
    }

    public String getName() {
        return name.getInput();
    }

    public void setName(String name) {
        this.name = new ApplicationName(name);
    }

    public String getDescription() {
        return description.getInput();
    }

    public void setDescription(String description) {
        this.description = new Description(description);
    }

    public String getApplicationUrl() {
        return applicationUrl.getInput();
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = new ApplicationUrl(applicationUrl);
    }

    public String getLogoUrl() {
        return logoUrl.getInput();
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = new LogoUrl(logoUrl);
    }

    public List<ApplicationAvailableRoleNames> getRoles() {
        return roles;
    }

    public void setRoles(List<ApplicationAvailableRoleNames> roles) {
        this.roles = roles;
    }

    public List<ApplicationAvailableOrganizationNames> getOrganizationNames() {
        return organizationNames;
    }

    public void setOrganizationNames(List<ApplicationAvailableOrganizationNames> orgs) {
        this.organizationNames = orgs;
    }

    public String getDefaultRoleName() {
        return defaultRoleName.getInput();
    }

    public void setDefaultRoleName(String defaultRoleName) {
        this.defaultRoleName = new DefaultRoleNames(defaultRoleName);
    }

    public String getDefaultOrganizationName() {
        return defaultOrganizationName.getInput();
    }

    public void setDefaultOrganizationName(String defaultOrganizationName) {
        this.defaultOrganizationName = new OrganizationName(defaultOrganizationName);
    }

    public DEFCON getMinimumApplicationDEFCON() {
        return this.security.getMinimumDEFCONLevel();
    }


    public ApplicationSecurity getSecurity() {
        return security;
    }

    public void setSecurity(ApplicationSecurity security) {
        this.security = security;
    }

    public String getCompany() {
        return company.getInput();
    }

    public void setCompany(String company) {
        this.company = new CompanyName(company);
    }

    public String getTags() {
        return tags.getInput();
    }

    public void setTags(String tags) {
        this.tags = new Tags(tags);
    }

    public List<String> getSupportedUserSessionLevels() {
        return supportedUserSessionLevels;
    }

    @JsonIgnore
    public List<UserSessionSecurityLevel> getSupportedUserSessionLevelList() {
        List<UserSessionSecurityLevel> userSessionSecurityLevels = new LinkedList<>();
        for (String s : supportedUserSessionLevels) {
            userSessionSecurityLevels.add(UserSessionSecurityLevel.valueOf(s));
        }
        return userSessionSecurityLevels;
    }

    @JsonIgnore
    public void setSupportedUserSessionLevelList(List<UserSessionSecurityLevel> supportedUserSessionLevels) {
        this.supportedUserSessionLevels = new LinkedList<>();
        for (UserSessionSecurityLevel userSessionSecurityLevel : supportedUserSessionLevels) {
            this.supportedUserSessionLevels.add(userSessionSecurityLevel.toString());
        }
    }

    public void setSupportedUserSessionLevels(List<String> supportedUserSessionLevelListString) {
        this.supportedUserSessionLevels = supportedUserSessionLevelListString;
    }

    public void addSupportedUserSessionLevels(String userSessionLevel) {
        supportedUserSessionLevels.add(userSessionLevel);
    }

    @JsonIgnore
    public boolean isFullTokenApplication() {
    	if(security!=null){
            return !(security.getUserTokenFilter());
        }
        return false;
    }


    public List<ApplicationAvailableOrganizationNames> getOrgs() {
        return organizationNames;
    }

    public void setOrgs(List<ApplicationAvailableOrganizationNames> orgs) {
        this.organizationNames = orgs;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (applicationUrl != null ? !applicationUrl.equals(that.applicationUrl) : that.applicationUrl != null)
            return false;
        if (logoUrl != null ? !logoUrl.equals(that.logoUrl) : that.logoUrl != null) return false;
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;
        if (defaultRoleName != null ? !defaultRoleName.equals(that.defaultRoleName) : that.defaultRoleName != null)
            return false;
        if (organizationNames != null ? !organizationNames.equals(that.organizationNames) : that.organizationNames != null) return false;
        if (defaultOrganizationName != null ? !defaultOrganizationName.equals(that.defaultOrganizationName) : that.defaultOrganizationName != null)
            return false;
        if (security != null ? !security.equals(that.security) : that.security != null) return false;
        return !(acls != null ? !acls.equals(that.acls) : that.acls != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (applicationUrl != null ? applicationUrl.hashCode() : 0);
        result = 31 * result + (logoUrl != null ? logoUrl.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (defaultRoleName != null ? defaultRoleName.hashCode() : 0);
        result = 31 * result + (organizationNames != null ? organizationNames.hashCode() : 0);
        result = 31 * result + (defaultOrganizationName != null ? defaultOrganizationName.hashCode() : 0);
        result = 31 * result + (security != null ? security.hashCode() : 0);
        result = 31 * result + (acls != null ? acls.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company='" + company + '\'' +
                ", tags='" + tags + '\'' +
                ", applicationUrl='" + applicationUrl + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", roles=" + roles +
                ", defaultRoleName='" + defaultRoleName + '\'' +
                ", organizationNames=" + organizationNames +
                ", defaultOrganizationName='" + defaultOrganizationName + '\'' +
                ", security=" + security +
                ", acls=" + acls +
                '}';
    }
}
