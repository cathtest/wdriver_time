package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.services.ControlPanelService;
import com.kate.mentoring.java.services.ExportExcelService;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class ExportStepDefs {
    private LogManager logManager = new LogManager();
    private ControlPanelService controlPanelService = new ControlPanelService();
    private ExportExcelService exportExcelService = new ExportExcelService();
    private String expectedURL;

    @And("^Click on Export to Excel$")
    public void clickOnExportToExcel() {
        this.expectedURL = DriverManager.getInstance().getDriver().getCurrentUrl();

        logManager.loggingInfo("Clicking Export to Excel button");
        controlPanelService.exportToExcelButtonClick();
    }

    @When("^Click on Export button$")
    public void clickOnExportButton() {
        logManager.loggingInfo("Clicking Export button");
        exportExcelService.exportButtonClick();
    }


    @Then("^Compare the project names$")
    public void compareTheProjectNames() {
        logManager.loggingInfo("Getting current URL");
        String redirectURL = DriverManager.getInstance().getDriver().getCurrentUrl();

        logManager.loggingInfo("Checking export to Excel");
        Assert.assertEquals(redirectURL, expectedURL, logManager.loggingSevere("Export button is not available"));
    }
}
