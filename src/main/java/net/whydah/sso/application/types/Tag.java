package net.whydah.sso.application.types;

import net.whydah.sso.ddd.model.customer.TagName;
import net.whydah.sso.ddd.model.customer.TagValue;

public class Tag {

    public static final String DEFAULTNAME = "UNNAMED";

    public Tag(String s) {
    	if(s.contains(":") && !s.contains("_")) {
    		s = s.replace(":", "_");
    	}
        String[] values = s.split("_");
        this.value = new TagValue(values[0].trim());
        this.name = new TagName(DEFAULTNAME);
        if (values.length > 1) {
            this.name = new TagName(values[0].toUpperCase().trim());
            this.value = new TagValue(values[1].trim());
        }
    }

    private TagName name;
    private TagValue value;

    public String getValue() {
        return value.getInput();
    }

    public String getName() {
        return name.getInput();
    }

    @Override
    public String toString() {
        if (name.getInput().equalsIgnoreCase(Tag.DEFAULTNAME)) {
            return value.getInput();
        } else {
            return name.getInput() + "_" + value.getInput();
        }
    }
}
