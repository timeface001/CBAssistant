package com.crossborder.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by s on 2018/9/11.
 */
public class PropertyUtil {
    private static Properties props;
    /*
     * 静态代码块加载  一次加载多次使用
     */
    static{
        loadProps();
    }

    private synchronized static void loadProps() {
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getResourceAsStream("/config.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据配置文件key获取配置参数值
     * @param key
     * @return
     */
    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
