package net.whydah.sso.application.types;

import net.whydah.sso.ddd.model.OrganizationId;
import net.whydah.sso.ddd.model.OrganizationName;


public class ApplicationAvailableOrganizationNames {
    private static final long serialVersionUID = -8050935915438484578L;
    private OrganizationId id=new OrganizationId("NotSet");
    private OrganizationName name=new OrganizationName("");

    private ApplicationAvailableOrganizationNames() {
    }

    public ApplicationAvailableOrganizationNames(String id, String name) {
        this.id = new OrganizationId(id);
        this.name = new OrganizationName(name);
    }

    public String getId() {
        return id.getId();
    }

    public void setId(String id) {
        this.id = new OrganizationId(id);
    }

    public String getName() {
        return name.getInput();
    }

    public void setName(String name) {
        this.name = new OrganizationName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationAvailableOrganizationNames org = (ApplicationAvailableOrganizationNames) o;

        if (!id.equals(org.id)) return false;
        return name.equals(org.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}

