package net.whydah.sso.extensions.crmcustomer.helpers;

import java.util.Arrays;
import java.util.Map;

public class MapKeyValidator {

    /**
     * Returns provided key if it actually exists in provided map.
     *
     * If not, the first key from map (sorted alphabetically) is returned.
     *
     * @param key
     * @param map
     * @return
     */
    public static String validateKey(String key, Map map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        if (key != null && map.containsKey(key)) {
            return key;
        }


        String[] keys = (String[]) map.keySet().toArray(new String[0]);
        Arrays.sort(keys); //Sorting to ensure same result every time

        return keys[0];
    }
}
