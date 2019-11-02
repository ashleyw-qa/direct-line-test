package direct.line.group.utils.driver;

import direct.line.group.utils.PropertyLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
A class used to setup and configure a Selenium WebDriver
 */
public class Driver {

    private WebDriver webDriver;

    public WebDriver getDriver() throws MalformedURLException {
        setupDriver();
        configureDriver();

        return webDriver;
    }

    private void setupDriver() throws MalformedURLException {
        String browser = PropertyLoader.getProperty("browser").toUpperCase();
        switch (browser) {
            case "CHROME":
                webDriver = setupChromeDriver();
                break;
            case "REMOTECHROME":
                webDriver = setupRemoteChromeDriver();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unable to find browser %s", browser));
        }
    }

    private WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private RemoteWebDriver setupRemoteChromeDriver() throws MalformedURLException {
        DesiredCapabilities caps = setupCapabilities();
        return new RemoteWebDriver(new URL(PropertyLoader.getProperty("hubUrl")), caps);
    }

    private DesiredCapabilities setupCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        return caps;
    }

    private void configureDriver() {
        webDriver
                .manage()
                .timeouts()
                .implicitlyWait(
                        Integer.valueOf(PropertyLoader.getProperty("timeout")), TimeUnit.SECONDS);
    }
}
