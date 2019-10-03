package net.whydah.sso.user.helpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TagsParser {
    private static final Logger log = LoggerFactory.getLogger(TagsParser.class);


	private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);

	public static String addTag(String key, Object value) throws JsonParseException, JsonMappingException, IOException {
		return addTag("{}", key, value);
	}

	public static boolean isJSONValid(String jsonInString ) {
		try {
			if(jsonInString!=null && !jsonInString.trim().equals("")) {
                JsonNode n = mapper.readTree(jsonInString);
                return n.isObject(); //true;
			}
		} catch (IOException e) {
            log.error("Unable tp parse Json:" + jsonInString + " -exception: ", e);
		}
		return false;
	}

	public static String addTag(String tags, String key, Object value) throws JsonParseException, JsonMappingException, IOException {

		if(tags==null  || tags.trim().equals("")) {
			tags = "{}";
		}
		if(!isJSONValid(tags)) {
			//convert the string to a json format
			String[] parts = tags.split("\\s*[,;:\\s+]\\s*");
			Map<String, String> m = new HashMap<>();
			for(String p : parts) {
				m.put("TAG_" + p, p);	
			}
			tags = mapper.writeValueAsString(m);
		}

		TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
		HashMap<String,Object> map = mapper.readValue(tags, typeRef); 
		map.put(key, value);
		return mapper.writeValueAsString(map);
	}

	public static Object getTag(String tags, String key) throws JsonParseException, JsonMappingException, IOException {
		TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
		HashMap<String,Object> map = mapper.readValue(tags, typeRef); 
		return map.get(key);
	}

	public static <T> T getTag(String tags, String key, Class<T> className) throws JsonParseException, JsonMappingException, IOException {
		TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
		HashMap<String,Object> map = mapper.readValue(tags, typeRef); 
		return mapper.convertValue( map.get(key), className);
	}

}
