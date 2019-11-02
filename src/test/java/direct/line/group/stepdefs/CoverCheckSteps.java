package direct.line.group.stepdefs;

import com.google.inject.Inject;
import direct.line.group.pages.CoverCheckPage;
import direct.line.group.utils.PropertyLoader;
import direct.line.group.utils.driver.LocalDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

/*
A class used to represent interactions with the CoverCheck Page
 */
public class CoverCheckSteps {

    private final World world;

    @Inject
    public CoverCheckSteps(final World world) {
        this.world = world;
    }

    @Given("I am on the cover check web page")
    public void iAmOnTheCoverCheckWebPage() {
        navigateToVehCheckPage();
    }

    private void navigateToVehCheckPage() {
        CoverCheckPage coverCheckPage = new CoverCheckPage(LocalDriverManager.getDriver());
        coverCheckPage.navigate();
    }

    @When("^I enter a known vehicle registration$")
    public void iEnterAKnownVehicleRegistration() {
        enterKnownVehicleReg();
    }

    private void enterKnownVehicleReg() {
        String knownReg = getKnownRegNumber();
        world.knownReg = knownReg;
        enterVehicleReg(knownReg);
    }

    @Then("I receive insurance cover details")
    public void i_receive_insurance_cover_details() {
        checkInsurCoverDisplayed();
    }

    private void checkInsurCoverDisplayed() {
        CoverCheckPage coverCheckPage = new CoverCheckPage(LocalDriverManager.getDriver());
        assertThat(coverCheckPage.result()).contains(world.knownReg);
    }

    @When("I enter an unknown vehicle registration")
    public void iEnterAnUnknownVehicleRegistration() {
        enterUnknownVehicleReg();
    }

    private void enterUnknownVehicleReg() {
        String unknownReg = getUnknownReg();
        world.unKnownReg = unknownReg;
        enterVehicleReg(unknownReg);
    }

    private String getUnknownReg() {
        return PropertyLoader.getProperty("unknownRegNumber");
    }

    @Then("I do not receive insurance cover details")
    public void iDoNotReceiveInsuranceCoverDetails() {
        checkInsurCoverNotDisplayed();
    }

    private void checkInsurCoverNotDisplayed() {
        CoverCheckPage coverCheckPage = new CoverCheckPage(LocalDriverManager.getDriver());
        assertThat(coverCheckPage.result()).doesNotContain(world.unKnownReg);
    }

    @When("I do not enter any text in the search field")
    public void iDoNotEnterAnyTextInTheSearchField() {
        enterVehicleReg("");
    }

    private void enterVehicleReg(final String reg) {
        CoverCheckPage coverCheckPage = new CoverCheckPage(LocalDriverManager.getDriver());
        coverCheckPage.enterVehicleReg(reg);
        assertThat(coverCheckPage.getVehicleReg()).isEqualTo(reg);
        coverCheckPage.submitVehicleReg();
    }

    @Then("I receive an error message")
    public void iReceiveAnErrorMessage() {
        checkTextValidationErrorDisplayed();
    }

    private void checkTextValidationErrorDisplayed() {
        CoverCheckPage coverCheckPage = new CoverCheckPage(LocalDriverManager.getDriver());
        assertThat(coverCheckPage.textValidationErrorDisplayed()).isTrue();
    }

    @When("I enter a known vehicle registration with whitespace characters")
    public void iEnterAKnownVehicleRegistrationWithWhitespaceCharacters() {
        enterKnownVehicleRegWithSpaces();
    }

    private void enterKnownVehicleRegWithSpaces() {
        String knownReg = getKnownRegNumber();
        String knownRegWithWhiteSpace = knownReg + "  ";
        world.knownReg = knownReg;
        enterVehicleReg(knownRegWithWhiteSpace);
    }

    private String getKnownRegNumber() {
        return PropertyLoader.getProperty("knownRegNumber");
    }
}
