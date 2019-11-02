package direct.line.group.utils;


import org.apache.commons.lang3.Validate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/*
Loads java property files which can be consumed by various classes across the suite
 */
public class PropertyLoader {

    private static Properties properties;
    private static final String environment;

    static {
        environment = System.getProperty("env");
        Validate.isTrue(isNotBlank(environment), "env cannot be blank or null");
    }

    public static String getProperty(String locatorProperty) {
        loadPropertyFile(environment + ".properties");

        overwriteWithEnvProperties();
        String property = properties.getProperty(locatorProperty);
        if (property == null || property.isEmpty()) {
            throw new IllegalArgumentException(String.format("Property %s is not set.", locatorProperty));
        }
        return property;

    }

    private static void loadPropertyFile(String strPath) {
        try (InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(strPath)) {
            properties = new Properties();

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + strPath + "' not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void overwriteWithEnvProperties() {
        properties.stringPropertyNames()
                .forEach(propertyName -> {
                    String systemPropertyValue = System.getProperty(propertyName);

                    if (systemPropertyValue != null) {
                        properties.setProperty(propertyName, systemPropertyValue);
                    }
                });
    }

}
