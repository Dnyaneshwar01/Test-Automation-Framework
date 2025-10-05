package com.utility;

import com.constants.Env;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    public static String readProperty(Env env, String propertyName) {
        File propFile = new File(System.getProperty("user.dir") + "//config//" + env + ".properties");
        FileReader fileReader = null;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = properties.getProperty(propertyName.toUpperCase());
        return value;
    }

//    String env = config.getProperty("application.environment");
//    String baseUrl = config.getProperty("env." + env + ".base.url");

}