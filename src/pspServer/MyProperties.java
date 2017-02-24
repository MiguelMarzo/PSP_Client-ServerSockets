package pspServer;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class to read a properties file (.properties).
 *
 * Singleton design pattern
 *
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 *
 */
public class MyProperties {

    /**
     * Unique instance of the class.
     */
    private static MyProperties properties = null;
    /**
     * Properties file with configuration information.
     */
    private String propertiesFile = "webserver.properties";

    /**
     * Private constructor to avoid the creation of instances from outside the
     * class.
     */
    private MyProperties() {
    }

    /**
     * Returns the unique instance.
     *
     * @return
     */
    public static MyProperties getProperties() {
        if (properties == null) {
            properties = new MyProperties();
        }
        return properties;
    }

    /**
     * Reads a property value from a given key.
     *
     * @param key key to find in the file.
     * @return the value.
     */
    public String readProperty(String key) {
        String result = "";
        try {
            FileInputStream file = new FileInputStream(propertiesFile);

            Properties p = new Properties();
            p.load(file);
            result = p.getProperty(key);
        } catch (Exception e) {
            System.err.println("Error while reading the property: " + key);
        }
        return result;
    }
}
