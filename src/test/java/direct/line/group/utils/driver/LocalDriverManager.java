package direct.line.group.utils.driver;

import org.openqa.selenium.WebDriver;

/*
A class used to store a thread specific Selenium WebDriver so that tests can be run in parallel
 */
public abstract class LocalDriverManager {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

}
