package arjityadav.appium.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import arjityadav.appium.constants.CommonConstants;

public class ConfigReader {

    /**
     * Retrieves the value associated with a given key from the config.properties file.
     *
     * @param key the key to look up in the properties file
     * @return the value associated with the key, or null if the key is not found
     */
    public static String getValue(String key) {
        Properties property = new Properties();
        FileInputStream propertyFile = null;

        try {
            // Attempt to load the properties file from the specified path
            propertyFile = new FileInputStream(CommonConstants.getConfigFilePath());
            property.load(propertyFile);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate config.properties file.");
        } catch (IOException e) {
            System.out.println("Unable to open or load config.properties file.");
        } finally {
            // Close the FileInputStream to avoid resource leaks
            if (propertyFile != null) {
                try {
                    propertyFile.close();
                } catch (IOException e) {
                    System.out.println("Unable to close the config.properties file.");
                }
            }
        }
        
        return property.getProperty(key);
    }
}