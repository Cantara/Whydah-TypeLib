package net.whydah.sso.basehelpers;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonPathHelper {

    private static final Logger log = LoggerFactory.getLogger(JsonPathHelper.class);


    public static List<String> findJsonpathList(String jsonString, String expression) throws PathNotFoundException {
        List<String> result = null;
        try {
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
            result = JsonPath.read(document, expression);

        } catch (Exception e) {
            log.warn("Failed to parse JSON. Expression {}, JSON {}, ", expression, jsonString, e);
        }
        return result;
    }

    public static String findJsonPathValue(String jsonString, String expression) throws PathNotFoundException {
        return JsonPath.parse(jsonString).read(expression);
    }



    public static String getStringFromJsonpathExpression(String jsonString, String expression) throws PathNotFoundException {
        String value = "";
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        String result = JsonPath.read(document, expression);
        value = result.toString();

        return value;
    }

    public static JSONArray getJsonArrayFromJsonpathExpression(String jsonString, String expression) throws PathNotFoundException {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        return JsonPath.read(document, expression);
    }

    /**
     * @param jsonString The JSON document you are parsing
     * @param expression An JSONPath expression
     * @return The resulting JSON Array converted to a String Array
     * @throws PathNotFoundException
     */
    public static String[] getStringArrayFromJsonpathExpression(String jsonString, String expression) throws PathNotFoundException {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        String resArray = JsonPath.read(document, expression);
        String resString = resArray.toString().substring(1, resArray.toString().lastIndexOf("]") - 1);
        resString.replace("},{", " ,");
        String[] array = resString.split(" ");
        return array;

    }

}
