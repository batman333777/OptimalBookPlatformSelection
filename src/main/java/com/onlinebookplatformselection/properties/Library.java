package com.onlinebookplatformselection.properties;

import java.io.FileInputStream;
import java.util.Properties;

public class Library {

    /*
      This method helps in reading CSV file
      @param filePath = csv file path
      @return properties file object
   */
    public static Properties readPropertiesFile(String fileName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return prop;
    }
}
