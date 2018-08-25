package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.services.ControlPanelService;
import com.kate.mentoring.java.services.FillingActivityService;
import com.kate.mentoring.java.services.Quantity;
import com.kate.mentoring.java.services.TableJournalService;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import static com.kate.mentoring.java.enums.ActivityNames.REQUIREMENTS;

public class FillingCellsStepdefs {

    private LogManager logManager = new LogManager();
    private TableJournalService tableJournalService = new TableJournalService();
    private FillingActivityService fillingActivityService = new FillingActivityService();
    private Quantity quantity = new Quantity();
    private ControlPanelService controlPanelService = new ControlPanelService();

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
