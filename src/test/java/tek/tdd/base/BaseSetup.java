package tek.tdd.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseSetup {
    // Declare a static final Logger named LOGGER, initialized using LogManager for the current class (BaseSetup).
// This logger is used for logging messages throughout this class.
    private static final Logger LOGGER = LogManager.getLogger(BaseSetup.class);
    private static WebDriver driver;
    private Properties properties;

    //Read Property File In Constructor of BaseSetup Class.
    public BaseSetup() {
        try {
            //File Path
            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/config/application-config.properties";

            LOGGER.info("Configuration File Path " + filePath);

            File propertyFile = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(propertyFile);
            properties = new Properties();
            properties.load(fileInputStream);
        }catch (IOException ex) {
            throw new RuntimeException("Config file is not accessible " + ex.getMessage());
        }
    }
// Define a LOGGER variable at the top of your class. It's typically used for logging messages.
// LOGGER is usually initialized with a logger instance specific to the class it's used in.

    private String getProperty(String key) {
        // Log a debug message indicating the property key being retrieved
        LOGGER.debug("Retrieving Property for key " + key);
        // Retrieve the property value for the given key from a properties object
        String property = this.properties.getProperty(key);
        // Log a debug message showing the value of the retrieved property
        LOGGER.debug("Property Value " + property);
        return property;
    }

    public void openBrowser() {

        String retailUrl = getProperty("ui.url");
        String browserType = getProperty("ui.browser");
        boolean isHeadless = Boolean.parseBoolean(getProperty("ui.isHeadless"));

        //Cross Browsing Setup.
        switch (browserType) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("Wrong Browser Type");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get(retailUrl);
    }

    public void quitBrowser() {
        LOGGER.debug("Closing Browser");
        if (driver != null)
            driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
