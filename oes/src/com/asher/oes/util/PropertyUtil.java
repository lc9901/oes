package com.asher.oes.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static Properties p = new Properties();

    static{
        InputStream is = null;

        try {
            is = PropertyUtil.class.getClassLoader().getResourceAsStream("app.properties");
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String propertyName) {
        return p.getProperty(propertyName);
    }

    public static String getStaticSource(){
        return p.getProperty("static_url");
    }
}