package project.properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Properties;

public class DriverManager {
    private static WebDriver driver;
    private static Properties properties = TestProperties.getINSTANCE().getProperties();

    public static WebDriver getWebDriver(){
        if (driver == null){
            initDriver();
        }
        return driver;
    }
    public static void initDriver() {
        System.setProperty(properties.getProperty("WEB_DRIVER"), properties.getProperty("WEB_DRIVER_PATH"));
        driver = new ChromeDriver();
        driver.get(properties.getProperty("HOSTNAME"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public static void closeDriver(){
        driver.quit();
    }
}
