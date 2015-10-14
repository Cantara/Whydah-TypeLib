package net.whydah.sso.application.types;

import java.io.Serializable;

/**
 * @author <a href="mailto:erik-dev@fjas.no">Erik Drolshammer</a> 2015-01-23
 */
public class ApplicationRoleVO implements Serializable {
    private static final long serialVersionUID = -8050935915438584578L;
    private String id;
    private String name;

    private ApplicationRoleVO() {
    }

    public ApplicationRoleVO(String id, String name) {
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

        ApplicationRoleVO role = (ApplicationRoleVO) o;

        if (!id.equals(role.id)) return false;
        if (!name.equals(role.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
