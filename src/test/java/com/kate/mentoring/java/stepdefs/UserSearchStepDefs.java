package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.services.ControlPanelService;
import com.kate.mentoring.java.utils.LogManager;
import com.kate.mentoring.java.utils.Sleeper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


public class UserSearchStepDefs {

    private LogManager logManager = new LogManager();
    private ControlPanelService controlPanelService = new ControlPanelService();

    @When("I click on current User button")
    public void iClickCurrentUserButton(){
        logManager.loggingInfo("Opening the list of users available on the project");
        controlPanelService.clickToShowProjectUsers();
    }

    @And("I click search button")
    public void iClickSearchButton() {
        logManager.loggingInfo("Clicking Search button");
        controlPanelService.clickSearchButton();
    }

    @When("^I send String <someName> to the Search field$")
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