package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.logics.CalendarLogics;
import com.kate.mentoring.java.services.CalendarService;
import com.kate.mentoring.java.services.ControlPanelService;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class ProjectForInvalidTimePeriodStepDefs {

    private LogManager logManager = new LogManager();
    private CalendarService calendarService = new CalendarService();
    private CalendarLogics calendarLogics = new CalendarLogics();
    private ControlPanelService controlPanelService = new ControlPanelService();


    @And("^I click on Calendar$")
    public void iClickOnCalendar() {
        logManager.loggingInfo("Clicking Calendar button");
        calendarService.calendarButtonClick();
    }

    @And("^I click on Calendar month view$")
    public void iClickOnCalendarMonthView() {
        logManager.loggingInfo("Opening Month view");
        calendarLogics.openMonthView();
    }

    @And("^I click on Calendar year view$")
    public void iClickOnCalendarYearView() {
        logManager.loggingInfo("Opening Year view");
        calendarLogics.openYearView();
    }

    @And("^I open the <year> chosen for check$")
    public void iOpenTheYearChosenForCheck() {
        logManager.loggingInfo("Opening the year chosen");
        calendarLogics.openYear(1998);
    }

    @And("^I choose the first month on the list\"$")
    public void iChooseTheFirstMonthOnTheList() {
        logManager.loggingInfo("Opening the first month in the year");
        calendarService.firstMonthInTheYearClick();
    }

    @And("^I choose the time range that is not current$")
    public void iChooseTheTimeRangeThatIsNotCurrent() {
        logManager.loggingInfo("Opening the time range that is not current");
        calendarService.clickNotCurrentTimeRange();
    }

    @When("^I click on User's list$")
    public void iClickOnUserSList() {
        logManager.loggingInfo("Clicking to show users available on the project");
        controlPanelService.clickToShowProjectUsers();
    }

    @Then("^I verify 'You have no project team members to be reviewed' text is displayed$")
    public void iVerifyIHaveNoProjectTeamMembersToBeReviewedTextIsDisplayed() {
        logManager.loggingInfo("Getting text from 'No projects available' message");
        String noProjectText = controlPanelService.getTextFromNoProjectAvailableMessage();

        logManager.loggingInfo("Checking projects members are not available");
        Assert.assertEquals(noProjectText,"You have no project team members to be reviewed",logManager.loggingSevere("There was an error, the text has been changed or projects stay available for invalid time period"));
    }

}
