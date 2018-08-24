package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.business_objects.UserModel;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import com.kate.mentoring.java.services.*;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.utils.LogManager;
import com.kate.mentoring.java.utils.Sleeper;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


public class PanelStepdefs {

    LogManager logManager = new LogManager();
    ControlPanelService controlPanelService = new ControlPanelService();
    LoginService loginService = new LoginService();

    @Given("I open time.epam.com")
    public void iOpenTime() {
        UserModel userModel = new UserModel(PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.USER_NAME.getValue()), PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.PASSWORD.getValue()));
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

    @When("I click on current User button")
    public void iClickCurrentUserButton(){
        logManager.loggingInfo("Opening the list of users available on the project");
        controlPanelService.clickToShowProjectUsers();
    }

    @When("I click search button")
    public void iClickSearchButton() {
        logManager.loggingInfo("Clicking Search button");
        controlPanelService.clickSearchButton();
    }

    @And("^I send String <someName> to the Search field$")
    public void iSendStringSomeNameToTheSearchField(String someName) {
        logManager.loggingInfo("Sending values to the Search field");
        controlPanelService.sendValueToSearch(someName);
        Sleeper.sleep(2);
    }


    @Then("^I check that String <someName> value sent is equal to the String value shown$")
    public void iCheckThatStringSomeNameValueSentIsEqualToTheStringValueShown(String someName) {
        logManager.loggingInfo("Getting text from user found");
        String resultSearchName = controlPanelService.getTextFromUserFound();

        logManager.loggingInfo("Checking search works correclty");

        Assert.assertEquals(resultSearchName, someName, logManager.loggingSevere("Search results do not coincide"));
    }
}