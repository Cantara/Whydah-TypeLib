package net.whydah.sso.application;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.application.mappers.ApplicationTagMapper;
import net.whydah.sso.application.types.Tag;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class ApplicationTagMapperTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationTagMapperTest.class);
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);


    @Test
    public void testSimpleTagMapping() {
        String simpletags = "HIDDEN, JURISDICTION_NORWAY";

        List<Tag> tagList = ApplicationTagMapper.getTagList(simpletags);
        assertTrue(tagList.size() == 2);

        log.debug(ApplicationTagMapper.toJson(tagList));
        log.debug(ApplicationTagMapper.toApplicationTagString(tagList));
    }

    @Test
    public void testLessSimpleTagMapping() {
        String simpletags = "HIDDEN, JURISDICTION_NORWAY, JURISDICTION_SWEDEN, OWNER:96905054, COMPANY:capraconsulting.no";

        List<Tag> tagList = ApplicationTagMapper.getTagList(simpletags);
        assertTrue(tagList.size() == 5);
        log.debug(ApplicationTagMapper.toJson(tagList));
        log.debug(ApplicationTagMapper.toApplicationTagString(tagList));
    }

    @Test
    public void testLessTagListMapping() throws Exception {
        String simpletags = "HIDDEN, JURISDICTION_NORWAY, JURISDICTION_SWEDEN, OWNER:96905054, COMPANY:capraconsulting.no";

        List<Tag> tagList = ApplicationTagMapper.getTagList(simpletags);
        assertTrue(tagList.size() == 5);

        Map<String, List<Tag>> stringListMap = ApplicationTagMapper.getTagMap(tagList);

        log.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stringListMap));
        log.debug(ApplicationTagMapper.toApplicationTagString(stringListMap));

    }

}
