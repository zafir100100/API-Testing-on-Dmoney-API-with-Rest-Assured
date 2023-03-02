package Setup;

import org.junit.Before;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Setup {
    public Properties prop;
    FileInputStream file;

    @Before
    public void initConfig() throws IOException {
        prop = new Properties();
        file = new FileInputStream("./src/test/resources/config.properties");
        prop.load(file);
    }
}
