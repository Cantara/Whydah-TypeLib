package net.whydah.sso.application.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whydah.sso.application.types.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ApplicationTagMapper {

    private static final Logger log = LoggerFactory.getLogger(ApplicationTagMapper.class);
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);


    public static List<Tag> getTagList(String s) {
        List<Tag> resolvedTags = new LinkedList<Tag>();
        if (s == null || s.length() < 1) {
            return resolvedTags;
        }
        String[] tags = s.split("[, ]+");
        for (String tag : tags) {
            resolvedTags.add(new Tag(tag));
        }
        return resolvedTags;
    }

    public static Map<String, List<Tag>> getTagMap(List<Tag> tagList) {
        Map<String, List<Tag>> stringTagListMap = new LinkedHashMap<String, List<Tag>>();
        for (Tag tag : tagList) {
            if (stringTagListMap.get(tag.getName()) == null) {
                stringTagListMap.put(tag.getName(), new LinkedList<Tag>());
            }
            List<Tag> tags = stringTagListMap.get(tag.getName());
            tags.add(tag);
            stringTagListMap.put(tag.getName(), tags);
        }
        return stringTagListMap;
    }

    public static String toJson(Map<String, List<Tag>> stringListMap) {

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stringListMap);
        } catch (Exception e) {

        }
        return "[]";

    }

    public static String toJson(List<Tag> tagList) {

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tagList);
        } catch (Exception e) {

        }
        return "[]";

    }

    public static String toApplicationTagString(List<Tag> tagList) {
        String returnString = "";
        if (tagList == null) {
            return returnString;
        }
        for (Tag tag : tagList) {
            returnString = returnString + tag.toString() + ", ";
        }
        if (returnString.length() < 2) {
            return returnString;
        }
        return returnString.substring(0, returnString.length() - 2);
    }

    public static String toApplicationTagString(Map<String, List<Tag>> stringListMap) {
        String returnString = "";
        if (stringListMap == null) {
            return returnString;
        }
        for (List<Tag> tagList : stringListMap.values()) {
            for (Tag tag : tagList) {
                returnString = returnString + tag.toString() + ", ";
            }
        }
        if (returnString.length() < 2) {
            return returnString;
        }
        return returnString.substring(0, returnString.length() - 2);
    }
}
