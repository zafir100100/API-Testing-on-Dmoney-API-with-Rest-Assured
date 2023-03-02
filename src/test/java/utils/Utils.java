package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;

public class Utils {
    public static void setEnvVariable(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public static int generateRandomNumber(int min, int max){
        int number= (int) Math.floor(Math.random()*(max-min)+min);
        return number;
    }
}
