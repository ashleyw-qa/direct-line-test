package direct.line.group.pages;

import direct.line.group.utils.PropertyLoader;
import org.apache.commons.lang3.Validate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/*
A class used to setup a Page Object and utilise Page based shared methods
 */
public abstract class PageBase {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final String path;
    private final String baseUrl;
    private final WebDriver webDriver;

    PageBase(final WebDriver webDriver, final String path) {
        Validate.notNull(webDriver, "webDriver cannot be null");
        Validate.isTrue(isNotBlank(path), "path cannot be empty");

        this.webDriver = webDriver;
        this.path = path;
        this.baseUrl = PropertyLoader.getProperty("baseUrl");

        PageFactory.initElements(webDriver, this);
    }

    public void navigate() {
        String url = baseUrl + path;
        logger.info(String.format("Navigating to %s", url));
        webDriver.get(url);
        waitForPageToBeReady(url);
    }

    private void waitForPageToBeReady(String url) {
        webDriverWait().until(ExpectedConditions.urlToBe(url));
        webDriverWait().until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
    }

    WebDriverWait webDriverWait() {
        return new WebDriverWait(webDriver, Integer.valueOf(PropertyLoader.getProperty("timeout")));
    }
}
