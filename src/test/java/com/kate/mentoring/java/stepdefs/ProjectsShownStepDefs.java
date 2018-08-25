package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.services.ControlPanelService;
import com.kate.mentoring.java.services.TableJournalService;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class ProjectsShownStepDefs {

    private LogManager logManager = new LogManager();
    private ControlPanelService controlPanelService = new ControlPanelService();
    private TableJournalService tableJournalService = new TableJournalService();


    @When("^I click on projects drop-down list$")
    public void iClickOnProjectsDropDownList() {
        logManager.loggingInfo("Choosing project");
        controlPanelService.chooseProject();
    }

    @Then("^I get text from the first project on Control Panel and in the Table and compare their values$")
    public void iGetTextFromTheFirstProjectOnControlPanelAndInTheTable() {
        logManager.loggingInfo("Verifying text from projects from Control Panel and Table");

        for (int i = 1; i < controlPanelService.getSizeOfProjectList(); i++) {
            String textProjectDropDown = controlPanelService.getTextFromProjectWithIndex(i);
            String textProjectCell = tableJournalService.getTextFromProjectCellWithIndex(i-1);

            Assert.assertEquals(textProjectDropDown, textProjectCell, logManager.loggingSevere("Elements do not coincide"));
        }
    }
}
