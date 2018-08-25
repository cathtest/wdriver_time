package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.services.ControlPanelService;
import com.kate.mentoring.java.services.WarningMessagesService;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class MonthViewPreservedStepDefs {

    private LogManager logManager = new LogManager();
    private ControlPanelService controlPanelService = new ControlPanelService();
    private WarningMessagesService warningMessagesService = new WarningMessagesService();
    private String verifyActiveRangeBeforeSwitch;
    private String verifyActiveRangeAfterSwitch;

    @And("^I click on the list of users$")
    public void iClickOnTheListOfUsers() {
        logManager.loggingInfo("Clicking to shown users available on the project");
        controlPanelService.clickToShowProjectUsers();
    }


    @And("^I click on any user from the list$")
    public void iClickOnAnyUserFromTheList() {
        logManager.loggingInfo("Clicking the first user on the list");
        controlPanelService.FirsUserOnTheListClick();
    }


    @And("^I choose Month view on Control panel$")
    public void iChooseMonthViewOnControlPanel() {
        logManager.loggingInfo("Opening Month view");
        controlPanelService.monthViewClick();

        logManager.loggingInfo("Getting text from the time range");
        this.verifyActiveRangeBeforeSwitch = controlPanelService.getTextFromSelectedRangeView();
    }


    @When("^I click to return to current User's page on the Warning message$")
    public void iClickToReturnToCurrentUserSPageOnTheWarningMessage() {
        logManager.loggingInfo("Clicking to return to the current user's page on Warning message");
        warningMessagesService.clickToReturnToCurrentUserPage();

        logManager.loggingInfo("Getting text from the time range");
        this.verifyActiveRangeAfterSwitch = controlPanelService.getTextFromSelectedRangeView();
    }


    @Then("^I verify the Month view is preserved$")
    public void iVerifyTheMonthViewIsPreserved() {
        logManager.loggingInfo("Checking month view is preserved");
        Assert.assertEquals(verifyActiveRangeBeforeSwitch, verifyActiveRangeAfterSwitch, logManager.loggingSevere("Month view is not preserved"));
    }
}