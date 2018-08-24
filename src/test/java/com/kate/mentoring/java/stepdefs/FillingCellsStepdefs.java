package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.business_objects.UserModel;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import com.kate.mentoring.java.services.*;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import static com.kate.mentoring.java.enums.ActivityNames.REQUIREMENTS;

public class FillingCellsStepdefs {

    LogManager logManager = new LogManager();
    TableJournalService tableJournalService = new TableJournalService();
    FillingActivityService fillingActivityService = new FillingActivityService();
    Quantity quantity = new Quantity();
    ControlPanelService controlPanelService = new ControlPanelService();
    LoginService loginService = new LoginService();


    @Given("^I open \"([^\"]*)\"$")
    public void iOpenTime() {
        UserModel userModel = new UserModel(
                PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.USER_NAME.getValue()),
                PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.PASSWORD.getValue())
        );

        loginService = new LoginService();
        logManager.loggingInfo("Logging in");
        String startURL = PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.START_URL.getValue());
        DriverManager.getInstance().getDriver().get(startURL);
        DriverManager.getInstance().getDriver().manage().window().maximize();
        loginService.nameFieldClick();
        loginService.nameFieldSendKeys(userModel.getUsername());
        loginService.passwordFieldClick();
        loginService.passwordFieldSendKeys(userModel.getPassword());
        loginService.submitButtonClick();
    }


    @When("^I click Add activity button$")
    public void iClickAddActivityButton() {
        logManager.loggingInfo("Clicking on the first activity");
        tableJournalService.clickOnTheFirstActivity();
    }

    @And("^I enter Activity name in the field$")
    public void iEnterActivityNameInTheField() {
        logManager.loggingInfo("Sending keys to the activity");
        fillingActivityService.fill(REQUIREMENTS.getValue());
    }


    @And("^Fill the cells with the <hours> of hours per cell$")
    public void fillTheCellsWithTheHoursOfHoursPerCell(String hours) {
        logManager.loggingInfo("Filling cells");
        quantity.fill(hours);
    }


    @Then("^I verify the cells are filled$")
    public void iVerifyTheCellsAreFilled() {
        logManager.loggingInfo("Checking how the cells are filled with hours and the activity field - with the name of the actvity");
        Assert.assertTrue(controlPanelService.checkWhetherCellIsFilled(), logManager.loggingSevere("The field is not filled"));
    }

}
