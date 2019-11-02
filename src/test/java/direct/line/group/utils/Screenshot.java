package direct.line.group.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Validate;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/*
A class providing the ability to take a screenshot of web page if a UI test fails
 */
public class Screenshot {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final WebDriver webDriver;
    private final String testName;
    private final String screenshotFolderPath;

    public Screenshot(WebDriver webDriver, String failingTestName) {
        Validate.notNull(webDriver, "webDriver cannot be null");
        Validate.isTrue(isNotBlank(failingTestName), "failingTestName cannot be blank or null");

        this.webDriver = webDriver;
        this.testName = failingTestName;
        this.screenshotFolderPath = PropertyLoader.getProperty("screenshotFolderPath");
    }

    public void capture() {
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        saveScreenshot(screenshot);
    }

    private void saveScreenshot(File screenshot) {
        String fileName = createFileName();
        logger.info(String.format("Saving Screenshot to %s", fileName));

        try {
            FileUtils.copyFile(screenshot, new File(fileName));
        } catch (IOException e) {
            logger.warning("Unable to capture screenshot" + e);
        }
    }

    private String createFileName() {
        return System.getProperty("user.dir") + screenshotFolderPath + getDateAndTime() + "-" + testName.replaceAll(" ","_") + ".png";
    }

    private String getDateAndTime() {
        return LocalDate.now().toString()
                + "/"
                + LocalTime.now().toString();
    }

}
