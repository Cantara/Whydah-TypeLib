package net.whydah.sso.application.types;

import net.whydah.sso.application.mappers.ApplicationTagMapper;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TagTest {

    @Test
    public void emptyTag() {
        Tag emptyTag = new Tag("", "");
        assertEquals("", emptyTag.getName());
        assertEquals("", emptyTag.getValue());
    }

    @Test
    public void nullTag() {
        Tag emptyTag = new Tag(null, null);
        assertNull(emptyTag.getName());
        assertNull(emptyTag.getValue());
    }

    @Test
    public void emptyTagBySerializedStringUsesDefaultName() {
        Tag emptyTag = new Tag("");
        assertEquals(Tag.DEFAULTNAME, emptyTag.getName());
        assertEquals("", emptyTag.getValue());
    }

    @Test
    public void legacyTag1() {
        Tag emptyTag = new Tag("NAME_Hei!Du"); // exclamation without valid escape sequence triggers legacy
        assertEquals("NAME", emptyTag.getName());
        assertEquals("Hei!Du", emptyTag.getValue());
    }

    @Test
    public void legacyTag2() {
        Tag emptyTag = new Tag("NAME:HeiDu"); // colon triggers legacy
        assertEquals("NAME", emptyTag.getName());
        assertEquals("HeiDu", emptyTag.getValue());
    }

    @Test
    public void legacyTag3() {
        Tag emptyTag = new Tag("NAME_Hei Du"); // space triggers legacy
        assertEquals("NAME", emptyTag.getName());
        assertEquals("Hei Du", emptyTag.getValue());
    }

    @Test
    public void nonLegacyTag() {
        Tag emptyTag = new Tag("NAME_HeiDu"); // Legacy not triggered, safe and treated the same anyhow
        assertEquals("NAME", emptyTag.getName());
        assertEquals("HeiDu", emptyTag.getValue());
    }

    @Test
    public void tagAggregationTest() {
        Tag tag1 = new Tag("NUM:BER_ONE", "  value 1");
        Tag tag2 = new Tag("N;UMBER__TWO", "se, +e !2");
        Tag tag3 = new Tag(",NUMBER_THREE", "an_d 3");
        List<Tag> tags = new LinkedList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        String tagString = ApplicationTagMapper.toApplicationTagString(tags);
        System.out.printf("tagString: %s%n", tagString);
        List<Tag> parsedTags = ApplicationTagMapper.getTagList(tagString);
        assertEquals(tag1.getName(), parsedTags.get(0).getName());
        assertEquals(tag1.getValue(), parsedTags.get(0).getValue());
        assertEquals(tag2.getName(), parsedTags.get(1).getName());
        assertEquals(tag2.getValue(), parsedTags.get(1).getValue());
        assertEquals(tag3.getName(), parsedTags.get(2).getName());
        assertEquals(tag3.getValue(), parsedTags.get(2).getValue());
    }
}
