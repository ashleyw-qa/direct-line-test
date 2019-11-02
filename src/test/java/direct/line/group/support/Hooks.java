package direct.line.group.support;

import direct.line.group.utils.Screenshot;
import direct.line.group.utils.driver.Driver;
import direct.line.group.utils.driver.LocalDriverManager;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.logging.Logger;

/*
A class used to setup and teardown the Cucumber tests
 */
public class Hooks {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Before
    public void setup(Scenario scenario) throws MalformedURLException {
        setupDriver();

        logger.info(String.format("Starting Test %s", scenario.getName()));
    }

    private void setupDriver() throws MalformedURLException {
        logger.info("Setting up WebDriver");
        Driver driver = new Driver();
        WebDriver webDriver = driver.getDriver();
        LocalDriverManager.setWebDriver(webDriver);
    }

    @After
    public void tearDown(Scenario scenario) {
        takeScreenshotIfFailed(scenario);

        logger.info("Quiting browser");
        LocalDriverManager.getDriver().quit();
    }

    private void takeScreenshotIfFailed(final Scenario scenario) {
        if (scenario.isFailed()) {
            logger.info("Taking Screenshot");
            new Screenshot(LocalDriverManager.getDriver(), scenario.getName()).capture();
        }
    }

}
