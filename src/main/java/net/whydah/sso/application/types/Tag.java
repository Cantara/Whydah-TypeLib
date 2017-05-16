package net.whydah.sso.application.types;

public class Tag {

    public static final String DEFAULTNAME = "UNNAMED";

    public Tag(String s) {
        s = s.replace(":", "_");
        String[] values = s.split("_");
        this.value = values[0];
        this.name = DEFAULTNAME;
        if (values.length > 1) {
            this.name = values[0];
            this.value = values[1];
        }

    }

    private String name;
    private String value;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
