package net.whydah.sso.ddd.model;

public class UID extends AbstractBaseId {

    public UID(String id) {
        super(id, 0, 36); //it is ok some for some identity structures to not have userId (yet)
    }

}
