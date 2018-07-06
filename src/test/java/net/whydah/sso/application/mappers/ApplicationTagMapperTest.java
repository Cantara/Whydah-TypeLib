package net.whydah.sso.application.mappers;

import net.whydah.sso.application.types.Tag;
import org.junit.Test;

import java.util.List;

public class ApplicationTagMapperTest {

    @Test
    public void thatItHandlesNPECorrectly() {
        List<Tag> tagList = ApplicationTagMapper.getTagList(null);
        System.out.println(tagList);
        tagList = null;
        String s = ApplicationTagMapper.toApplicationTagString(tagList);
    }
}