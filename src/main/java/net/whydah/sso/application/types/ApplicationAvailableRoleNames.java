package net.whydah.sso.application.types;

import net.whydah.sso.ddd.model.userrole.RoleId;
import net.whydah.sso.ddd.model.userrole.RoleName;

import java.io.Serializable;

/**
 * @author <a href="mailto:erik-dev@fjas.no">Erik Drolshammer</a> 2015-01-23
 */
public class ApplicationAvailableRoleNames implements Serializable {
    private static final long serialVersionUID = -8050935915438584578L;
    private RoleId id=new RoleId("NotSet");
    private RoleName name=new RoleName("");

    public ApplicationAvailableRoleNames(){}
    public ApplicationAvailableRoleNames(String id, String name) {
        this.id = new RoleId(id);
        this.name = new RoleName(name);
    }

    public String getId() {
        return id.getId();
    }

    public void setId(String id) {
        this.id = new RoleId(id);
    }

    public String getName() {
        return this.name.getInput();
    }

    public void setName(String name) {
        this.name = new RoleName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationAvailableRoleNames role = (ApplicationAvailableRoleNames) o;

        if (!id.equals(role.id)) return false;
        return name.equals(role.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
