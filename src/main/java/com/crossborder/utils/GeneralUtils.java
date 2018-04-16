package com.crossborder.utils;

import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by fengsong on 2018/4/15.
 */
public class GeneralUtils {

    public static String absPath(){
        return ClassUtils.getDefaultClassLoader().getResource("").getPath();
    }


    public static Map<String, Object> genMap(Object... keyAndValues) {
        Map<String, Object> result = new HashMap<>();
        if (keyAndValues != null && keyAndValues.length % 2 == 0) {
            for (int i = 0; i < keyAndValues.length; i++) {
                result.put(keyAndValues[i].toString(), keyAndValues[++i]);
            }
        }
        return result;
    }

    public static String getUUID32() {

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();

    }

    public static String getUUID16() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }


}
