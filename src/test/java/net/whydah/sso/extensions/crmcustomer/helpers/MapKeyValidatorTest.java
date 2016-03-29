package net.whydah.sso.extensions.crmcustomer.helpers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapKeyValidatorTest {


    private Map<String, Object> map;
    final String validKey1 = "abcd";
    final String validKey2 = "qwerty";

    @Before
    public void setUp() {
        map = new HashMap<>();
        map.put(validKey1, new Object());
        map.put(validKey2, new Object());
    }

    @Test
    public void testNoDefaultKey() {
        String defaultValue = null;
        defaultValue = MapKeyValidator.validateKey(defaultValue, map);
        assertEquals(validKey1, defaultValue);
    }

    @Test
    public void testValidDefaultKey() {
        String defaultValue = validKey2;
        defaultValue = MapKeyValidator.validateKey(defaultValue, map);
        assertEquals(validKey2, defaultValue);
    }


    @Test
    public void testInvalidKey() {
        String defaultValue = "dummykey";
        defaultValue = MapKeyValidator.validateKey(defaultValue, map);
        assertEquals(validKey1, defaultValue);
    }

    @Test
    public void testDefaultMapIsNull() {
        Map <String, Object> nullMap = null;

        String defaultValue = MapKeyValidator.validateKey("dummykey", nullMap);
        assertEquals(null, defaultValue);

        defaultValue = MapKeyValidator.validateKey(null, nullMap);
        assertEquals(null, defaultValue);

        defaultValue = MapKeyValidator.validateKey(validKey1, nullMap);
        assertEquals(null, defaultValue);
    }

    @Test
    public void testDefaultMapIsEmpty() {
        Map <String, Object> emptyMap = new HashMap<>();

        String defaultValue = MapKeyValidator.validateKey("dummykey", emptyMap);
        assertEquals(null, defaultValue);

        defaultValue = MapKeyValidator.validateKey(null, emptyMap);
        assertEquals(null, defaultValue);

        defaultValue = MapKeyValidator.validateKey(validKey1, emptyMap);
        assertEquals(null, defaultValue);
    }

}