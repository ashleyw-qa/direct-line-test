package direct.line.group.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
A Page Object that represents the CoverCheck homepage
 */
public class CoverCheckPage extends PageBase {

    private static final String PATH = "/";

    @FindBy(id = "vehicleReg")
    private WebElement vehicleReg;

    @FindBy(name = "btnfind")
    private WebElement findButton;

    @FindBy(className = "result")
    private WebElement result;

    @FindBy(className = "error-required")
    private WebElement textValidationError;


    public CoverCheckPage(final WebDriver webDriver) {
        super(webDriver, PATH);
    }

    public void enterVehicleReg(String reg) {
        vehicleReg.sendKeys(reg);
    }

    public String getVehicleReg() {
        return vehicleReg.getAttribute("value");
    }

    public void submitVehicleReg() {
        findButton.click();
    }

    public String result() {
        return result.getText();
    }

    public boolean textValidationErrorDisplayed() {
        return textValidationError.isDisplayed();
    }
}
