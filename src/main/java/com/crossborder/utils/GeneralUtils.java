package com.crossborder.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengsong on 2018/4/15.
 */
public class GeneralUtils {

    public static Map<String, Object> genMap(Object... keyAndValues) {
        Map<String, Object> result = new HashMap<>();
        if (keyAndValues != null && keyAndValues.length % 2 == 0) {
            for (int i = 0; i < keyAndValues.length; i++) {
                result.put(keyAndValues[i].toString(), keyAndValues[++i]);
            }
        }
        return result;
    }

}
