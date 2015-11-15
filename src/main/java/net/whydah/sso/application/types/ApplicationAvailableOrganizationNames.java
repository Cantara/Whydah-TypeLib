package net.whydah.sso.application.types;


public class ApplicationAvailableOrganizationNames {
    private static final long serialVersionUID = -8050935915438584578L;
    private String id;
    private String name;

    private ApplicationAvailableOrganizationNames() {
    }

    public ApplicationAvailableOrganizationNames(String id, String name) {
        this.id = id;
        this.name = name;
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

