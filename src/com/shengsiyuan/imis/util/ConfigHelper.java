package com.shengsiyuan.imis.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigHelper {

    //作为Properties缓存
    public static Map<String, Properties> conf = new HashMap<String, Properties>();
    
    public static URL findResource(String configName) {
        
        URL resourceURL = null;
        resourceURL = Thread.currentThread().getContextClassLoader().getResource(configName);
        if (resourceURL != null) {
            return resourceURL;
        }
        
        resourceURL = ConfigHelper.class.getResource(configName);
        
        if (resourceURL != null) {
            return resourceURL;
        }
        
        resourceURL = ClassLoader.getSystemClassLoader().getResource(configName);
        
        return resourceURL;
        
    }
    
    public static Properties getProperties(String name) throws IOException {
        Properties prop = conf.get(name);
        if (prop == null) {
            InputStream is = findResource(name).openStream();
            prop = new Properties();
            prop.load(is);
            is.close();
            conf.put(name, prop);
            return prop;
        }
        return prop;
    }
    
    
    
}
