package net.whydah.sso.application.types;

import net.whydah.sso.basehelpers.EscapeHelper;
import net.whydah.sso.ddd.model.customer.TagName;
import net.whydah.sso.ddd.model.customer.TagValue;

import java.io.Serializable;

public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULTNAME = "UNNAMED";

    public static EscapeHelper ESCAPE_HELPER = new EscapeHelper('!',
            ',', 'c',
            ':', 'C',
            ' ', 's',
            ';', 'S',
            '_', 'u'
    );

    public Tag(String encodedTag) {
        if (encodedTag.contains(",") || encodedTag.contains(":") || encodedTag.contains(" ") || encodedTag.contains(";")
                || (encodedTag.contains("!") && !(encodedTag.contains("!!") || encodedTag.contains("!c") || encodedTag.contains("!C") || encodedTag.contains("!s") || encodedTag.contains("!S") || encodedTag.contains("!u")))) {
            // Looks like the tag is not encoded, probably a value that was there before we used the current
            // escaping technique. Use old way of interpreting encoded tag.
            if (encodedTag.contains(":") && !encodedTag.contains("_")) {
                encodedTag = encodedTag.replace(":", "_");
            }
            String[] values = encodedTag.split("_");
            this.value = new TagValue(values[0].trim());
            this.name = new TagName(DEFAULTNAME);
            if (values.length > 1) {
                this.name = new TagName(values[0].toUpperCase().trim());
                this.value = new TagValue(values[1].trim());
            }
        } else {
            // use escaping
            String[] values = encodedTag.split("_");
            if (values.length == 1) {
                this.name = new TagName(DEFAULTNAME);
                this.value = new TagValue(ESCAPE_HELPER.decode(values[0].trim()));
            } else if (values.length > 1) {
                this.value = new TagValue(ESCAPE_HELPER.decode(values[1].trim()));
                this.name = new TagName(ESCAPE_HELPER.decode(values[0].trim()));
            }
        }
    }

    public Tag(String name, String value) {
        this.name = new TagName(name);
        this.value = new TagValue(value);
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
            return ESCAPE_HELPER.encode(value.getInput());
        } else {
            return ESCAPE_HELPER.encode(name.getInput()) + "_" + ESCAPE_HELPER.encode(value.getInput());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        return value != null ? value.equals(tag.value) : tag.value == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
