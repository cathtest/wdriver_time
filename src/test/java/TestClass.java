import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import unit.DriverManager;
import utils.LogManager;
import utils.Sleeper;
import unit.TestCase;
import java.util.List;
import java.util.logging.Logger;

public class TestClass extends TestCase {

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day enter the quantity of hours")
    public void checkAddHoursWorkCorrectly() {
        logManager.loggingInfo("Clicking on the first activity");
        tableJournalService.clickOnTheFirstActivity();
        logManager.loggingInfo("Sending keys to the activity");
        tableJournalService.sendKeysToTheActivity("Daily sync-up");
        logManager.loggingInfo("Filling cells");
        tableJournalService.fillCells("0.5");
        logManager.loggingInfo("Checking how the cells are filled with hours");
        Assert.assertTrue(controlPanelService.checkWhetherCellIsFilled(), "The field is not filled");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on projects drop-down list" +
            "\n 3. Get text from the first project (except 'All')" +
            "\n 4. Get text from the first project in the table" +
            "\n 5. Compare the project names" +
            "\n 6. Do the same for the rest of the projects")
    public void checkProjectsInProjectListAreShownOnTheDashboard() {
        logManager.loggingInfo("Choosing project");
        controlPanelService.chooseProject();
        logManager.loggingInfo("Verifying text from projects from Control Panel and Table");
        for (int i = 1; i < controlPanelService.getSizeOfProjectList(); i++) {
            String textProjectDropDown = controlPanelService.getTextFromProjectWithIndex(i);
            String textProjectCell = tableJournalService.getTextFromProjectCellWithIndex(i-1);
            Assert.assertEquals(textProjectDropDown, textProjectCell, "Elements do not coincide");
        }
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. Get user's total reported time" +
            "\n 4.Compare the number of actual hours with the number of expected hours")
    public void checkCurrentUserFilledTime() {
        logManager.loggingInfo("Clicking to open Month view");
        controlPanelService.monthViewClick();
        logManager.loggingInfo("Getting text from total hours element");
        String totalForCurrentUser = tableJournalService.getTextFromTotalHours();
        String regEx = "^Total\\s((152)\\W)(?=152)|((160)\\W)(?=160)|((168)\\W)(?=168)";
        Assert.assertTrue(totalForCurrentUser.matches(regEx), "You haven't filled all the hours");

    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. Get user total reported time" +
            "\n 4. Add new activity" +
            "\n 5. Add an hour for that activity" +
            "\n 6. Save changes" +
            "\n 7. Verify that total time is changed")
    public void checkTotalTimeIsChanged() {
        logManager.loggingInfo("Clicking to open Month view");
        controlPanelService.monthViewClick();
        logManager.loggingInfo("Getting text from total hours element");
        String totalTimeBeforeChanges = tableJournalService.getTextFromTotalHours();
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Sending keys to the activity");
        tableJournalService.sendKeysToTheActivity("Some test activity");
        logManager.loggingInfo("Logging hours for today");
        tableJournalService.logHoursForToday("1");
        logManager.loggingInfo("Saving hours");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Getting text from total hours element");
        String totalTimeAfterChanges = tableJournalService.getTextFromTotalHours();
        logManager.loggingInfo("Checking that the total reported time has been changed after logging some new hours");
        Assert.assertSame(totalTimeBeforeChanges, totalTimeAfterChanges);
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. Click on the user selector" +
            "\n 4. For each user: " +
            "\n 4.1 open user profile" +
            "\n 4.2 get user total reported time")
    public void checkAllUsersAreAvailableAndThersTotalHours() {
        logManager.loggingInfo("Clicking to open Month view");
        controlPanelService.monthViewClick();
        logManager.loggingInfo("Opening the list of users available on the project");
        controlPanelService.clickToShowProjectUsers();
        logManager.loggingInfo("Getting size of team members");
        int quantityOfTeamMembers = controlPanelService.sizeOfTeamMembersList();
        String regEx = "^Total\\s((152)\\W)(?=152)|((160)\\W)(?=160)|((168)\\W)(?=168)";
        for (int i = 0; i < quantityOfTeamMembers; i++) {
            logManager.loggingInfo("Clicking on the user with index");
            controlPanelService.clickTeamMemberWithIndex(i);
            logManager.loggingInfo("Getting text from total hours element");
            String totalHoursForUser = tableJournalService.getTextFromTotalHours();
            logManager.loggingInfo("Opening the list of users available on the project");
            controlPanelService.clickToShowProjectUsers();
            logManager.loggingInfo("Getting list of all the users on the project");
            List<WebElement> listOfTeamMembers = controlPanelService.getListOfTeamMembers();
            listOfTeamMembers.forEach(member->{
                logManager.loggingInfo("Checking certain user has reported his time");
                Assert.assertTrue(totalHoursForUser.matches(regEx), "User hasn't filled his time"); }
            );
        }
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Export to Excel" +
            "\n 3. Click on Export button" +
            "\n 5. Compare the project names")
    public void exportExcel() {
        logManager.loggingInfo("Getting current URL");
        String expectedURL = DriverManager.getInstance().getDriver().getCurrentUrl();
        logManager.loggingInfo("Clicking Export to Excel button");
        controlPanelService.exportToExcelButtonClick();
        logManager.loggingInfo("Clicking Export button");
        exportExcelService.exportButtonClick();
        logManager.loggingInfo("Getting current URL");
        String redirectURL = DriverManager.getInstance().getDriver().getCurrentUrl();
        logManager.loggingInfo("Checking export to Excel");
        Assert.assertEquals(redirectURL, expectedURL, "Export button is not available");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on 2 weeks button" +
            "\n 3. Verify total time is changed")
    public void checkTwoWeeksButtonWorksCorrectly() {
        logManager.loggingInfo("Getting text from total hours element");
        String totalTimeBeforeChanges = tableJournalService.getTextFromTotalHours();
        logManager.loggingInfo("Clicking Two Weeks period");
        controlPanelService.clickTwoWeeksPeriodButton();
        logManager.loggingInfo("Getting text from total hours element");
        String totalTimeAfterChanges = tableJournalService.getTextFromTotalHours();
        logManager.loggingInfo("Checking time range has been changed");
        Assert.assertSame(totalTimeBeforeChanges, totalTimeAfterChanges, "Time range hasn't been changed");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click the Current User button at the top left corner" +
            "\n 3. Click search button" +
            "\n 4. Enter existing user name" +
            "\n 5. Verify User is found")
    public void checkUserSearchIsCorrect() {
        String someName = "Alexey Alexandrov";
        logManager.loggingInfo("Opening the list of users available on the project");
        controlPanelService.clickToShowProjectUsers();
        logManager.loggingInfo("Clicking Search button");
        controlPanelService.clickSearchButton();
        logManager.loggingInfo("Sending values to the Search field");
        controlPanelService.sendValueToSearch(someName);
        Sleeper.sleep(2);
        logManager.loggingInfo("Getting text from user found");
        String resultSearchName = controlPanelService.getTextFromUserFound();
        logManager.loggingInfo("Checking search works correclty");
        Assert.assertEquals(resultSearchName, someName, "Search results do not coincide");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours" +
            "\n 7. Refresh the page")
    public void checkDataNotSavedAfterRefreshingPage() {
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Sending keys to the activity");
        tableJournalService.sendKeysToTheActivity("Another test activity");
        logManager.loggingInfo("Filling cells with hours");
        tableJournalService.fillCells("0.5");
        logManager.loggingInfo("Refreshing the page");
        DriverManager.getInstance().getDriver().navigate().refresh();
        logManager.loggingInfo("Checking data is not saved after refreshing the page");
        Assert.assertTrue(tableJournalService.checkWhetherWorkingDayListIsEmpty(), "There was an error, the field stays filled");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours" +
            "\n 7. Click Cancel Button")
    public void checkDataNotSavedAfterClickingCancel() {
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Sending keys to the activity");
        tableJournalService.sendKeysToTheActivity("Another one test activity");
        logManager.loggingInfo("Filling cells with hours");
        tableJournalService.fillCells("0.5");
        logManager.loggingInfo("Clicking Cancel button");
        controlPanelService.cancelButtonClick();
        logManager.loggingInfo("Checking data is not saved after clicking Cancel button");
        Assert.assertTrue(tableJournalService.checkWhetherWorkingDayListIsEmpty(), "There was an error, the field stays filled");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Click today button" +
            "\n 5. Verify date on the calendar button stays the same as it was at the beginning")
    public void checkTodayButtonWorksCorrectly() {
        logManager.loggingInfo("Getting text from Calendar button");
        String dateBeforeSwitch = calendarService.getTextFromCalendarButton();
        logManager.loggingInfo("Clicking Calendar button");
        calendarService.calendarButtonClick();
        logManager.loggingInfo("Clicking time range that is not current");
        calendarService.clickNotCurrentTimeRange();
        logManager.loggingInfo("Clicking today button");
        controlPanelService.todayButtonClick();
        logManager.loggingInfo("Getting text from Calendar button");
        String dateAfterSwitch = calendarService.getTextFromCalendarButton();
        logManager.loggingInfo("Checking time range is changed");
        Assert.assertEquals(dateBeforeSwitch, dateAfterSwitch, "There was an error, date hasn't been changed");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Verify date of the columns in the table has been changed")
    public void checkTableIsShownAccordingToCalendarWeek() {
        logManager.loggingInfo("Getting text from the column of the working day");
        String columnBeforeSwitch = tableJournalService.getTextFromWorkingDayColumnDate();
        logManager.loggingInfo("Clicking Calendar button");
        calendarService.calendarButtonClick();
        logManager.loggingInfo("Clicking time range that is not current");
        calendarService.clickNotCurrentTimeRange();
        logManager.loggingInfo("Getting text from the column of the working day");
        String columnAfterSwitch = tableJournalService.getTextFromWorkingDayColumnDate();
        logManager.loggingInfo("Checking the column has been changed");
        Assert.assertNotSame(columnBeforeSwitch, columnAfterSwitch, "There was an error, date hasn't been changed");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar" +
            "\n 3. Click on Calendar month view" +
            "\n 4. Click on Calendar year view" +
            "\n 5. Choose the following year" +
            "\n 6. Choose any month" +
            "\n 7. Choose the time range that is not current" +
            "\n 8. Go to Month view on Control Panel" +
            "\n 9. Return to Week view again" +
            "\n 10.Verify that time range is shown in accordance with the year and month chosen on Calendar")
    public void checkDateIsPreservedAfterSwitchingToAnotherTimeRange() {
        logManager.loggingInfo("Getting text from Calendar button");
        String dateBeforeYearSwitch = calendarService.getTextFromCalendarButton();
        logManager.loggingInfo("Clicking Calendar button");
        calendarService.calendarButtonClick();
        logManager.loggingInfo("Clicking Month view");
        calendarLogics.openMonthView();
        logManager.loggingInfo("Clicking Year view");
        calendarLogics.openYearView();
        logManager.loggingInfo("Choosing the following year");
        calendarService.FollowingYearClick();
        logManager.loggingInfo("Choosing the first month in the year");
        calendarService.firstMonthInTheYearClick();
        logManager.loggingInfo("Clicking time range that is not current");
        calendarService.clickNotCurrentTimeRange();
        logManager.loggingInfo("Clicking Month view");
        controlPanelService.monthViewClick();
        logManager.loggingInfo("Clicking Week view");
        controlPanelService.weekViewClick();
        logManager.loggingInfo("Getting text from Calendar button");
        String dateAfterYearSwitch = calendarService.getTextFromCalendarButton();
        logManager.loggingInfo("Verifying that time range is shown in accordance with the year and month chosen on Calendar");
        Assert.assertNotEquals(dateBeforeYearSwitch, dateAfterYearSwitch, "Date is not preserved");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
                    "\n 2. Add certain activity" +
                    "\n 3. Click Save button" +
                    "\n 4. Click to Add activity again" +
                    "\n 5. Add activity with the same name" +
                    "\n 6. Close warning message" +
                    "\n 7. Click Save button again")
    public void checkTwoEqualActivitiesCannotBeAdded() {
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Sending keys to the activity");
        tableJournalService.sendKeysToTheActivity("Ordinary test activity");
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Sending the same keys to the newly created activity");
        tableJournalService.sendKeysToTheActivity("Ordinary test activity");
        logManager.loggingInfo("Clicking Save button");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Closing warning message");
        warningMessagesService.clickToCloseWarningMesssage();
        logManager.loggingInfo("Clicking Save button");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Checking that the hours can't be added to the activity with the same name");
        Assert.assertTrue(warningMessagesService.checkWarningMessageIsDisplayed(), "There was an error, the hours for the Activity with the same name have been added");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Add certain activity" +
            "\n 3. Add hours for that activity" +
            "\n 4. Choose overtime mode" +
            "\n 5. Choose one of the cells as an overtime cell" +
            "\n 6. Click to Save" +
            "\n 7. Choose Overtime mode again" +
            "\n 8. Click on the cell chosen as an overtime cell" +
            "\n 9. Click to Cancel overtime" +
            "\n 10. Click to save" +
            "\n 11. Renew the page and verify overtime stays canceled", enabled = false)
    public void checkOvertimeHoursCanBeCanceled() {
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Sending keys to the activity");
        tableJournalService.sendKeysToTheActivity("Regular test activity");
        logManager.loggingInfo("Filling cells with hours");
        tableJournalService.fillCells("0.5");
        logManager.loggingInfo("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Switching on Overtime mode");
        tableJournalService.overtimeModeSwitchOn();
        logManager.loggingInfo("Clicking on the cell where Overtime is not enabled");
        tableJournalService.overtimeNotEnabledCellClick();
        logManager.loggingInfo("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Clicking on the cell where Overtime has already been enabled");
        tableJournalService.overTimeEnabledClick();
        logManager.loggingInfo("Clicking Cancel button on Overtime page");
        overtimeCancellingService.cancelOvertimeButtonClick();
        logManager.loggingInfo("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Refreshing the page");
        DriverManager.getInstance().getDriver().navigate().refresh();
        Sleeper.sleep(2);
        logManager.loggingInfo("Checking that Overtime cells can be cancelled");
        Assert.assertFalse(tableJournalService.checkWhetherOverTimeSumbittedCellIsDisplayed(), "Overtimes cannot be cancelled");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Add certain activity" +
            "\n 3. Add hours for that activity" +
            "\n 4. Choose overtime mode" +
            "\n 5. Choose one of the cells as an overtime cell" +
            "\n 6. Click to Save" +
            "\n 7. Verify that Overtime hours have been added to the total reported time", enabled = false)
    public void checkOvertimeHoursCanBeSaved() {
        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);
        logManager.loggingInfo("Sending keys to the activity");
        tableJournalService.sendKeysToTheActivity("Next activity");
        logManager.loggingInfo("Filling cells with hours");
        tableJournalService.fillCells("0.5");
        logManager.loggingInfo("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Switching on Overtime mode");
        tableJournalService.overtimeModeSwitchOn();
        Sleeper.sleep(2);
        logManager.loggingInfo("Clicking on the cell where Overtime is not enabled");
        tableJournalService.overtimeNotEnabledCellClick();
        logManager.loggingInfo("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();
        logManager.loggingInfo("Getting text from Overtime Info element");
        String overtimeCheck = tableJournalService.getTextFromTotalOverTimeInfo();
        String regex = "^Overtime\\s([0-9]\\W[0-9])|([0-9])|([0-9][0-9])";
        logManager.loggingInfo("Checking Overtime hours are saved");
        Assert.assertTrue(overtimeCheck.matches(regex), "Overtime hours are not saved");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click Export to Excel" +
            "\n 3. Click on the first Calendar" +
            "\n 4. Click on Calendar month view" +
            "\n 5. Click on Calendar year view" +
            "\n 6. Choose last year period" +
            "\n 7. Choose last year's first month" +
            "\n 8. Choose a day" +
            "\n 9. Click on Export button")
    public void checkExportUnavailableForMoreThanYearPeriod() {
        logManager.loggingInfo("Clicking Export to Excel button");
        controlPanelService.exportToExcelButtonClick();
        logManager.loggingInfo("Clicking on the right calendar");
        exportExcelService.rightCalendarClick();
        logManager.loggingInfo("Opening Month view");
        calendarLogics.openMonthView();
        logManager.loggingInfo("Opening Year view");
        calendarLogics.openYearView();
        logManager.loggingInfo("Opening the last year on the list");
        calendarService.openLastYearOnTheList();
        logManager.loggingInfo("Opening the first month in the year");
        calendarService.firstMonthInTheYearClick();
        logManager.loggingInfo("Choosing the first day in the month");
        calendarService.firstDayInTheMonthClick();
        logManager.loggingInfo("Getting current URL");
        String expectedURL = DriverManager.getInstance().getDriver().getCurrentUrl();
        logManager.loggingInfo("Clicking Export button");
        exportExcelService.exportButtonClick();
        logManager.loggingInfo("Verifying that the URLs got before and after clicking Export button are equal");
        Assert.assertEquals(DriverManager.getInstance().getDriver().getCurrentUrl(), expectedURL, "There was an error: export is executed with the time range that is more than year");
        logManager.loggingInfo("Checking whether Export button is enabled");
        Assert.assertFalse(exportExcelService.checkExportButtonIsEnabled(), "There is an issue, button is enabled");
        logManager.loggingInfo("Checking whether Export block is shown itself");
        Assert.assertTrue(exportExcelService.checkExportBlockIsDisplayed(), "There was an error, excel block is not shown!");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar" +
            "\n 3. Click on Calendar month view" +
            "\n 4. Click on Calendar year view" +
            "\n 5. Click on left arrow till the seventies appear " +
            "\n 6. Choose the first year on the list" +
            "\n 7. Choose the first month on the list" +
            "\n 8. Choose the first time range on the list" +
            "\n 9. Click on User's list" +
            "\n 10. Verify 'You have no project team members to be reviewed' text is displayed")
    public void checkThereIsNoProjectAvailableForInvalidTimePeriod() {
        logManager.loggingInfo("Clicking Calendar button");
        calendarService.calendarButtonClick();
        Sleeper.sleep(2);
        logManager.loggingInfo("Opening Month view");
        calendarLogics.openMonthView();
        logManager.loggingInfo("Opening Year view");
        calendarLogics.openYearView();
        logManager.loggingInfo("Opening the year chosen");
        calendarLogics.openYear(1998);
        logManager.loggingInfo("Opening the first month in the year");
        calendarService.firstMonthInTheYearClick();
        logManager.loggingInfo("Opening the time range that is not current");
        calendarService.clickNotCurrentTimeRange();
        logManager.loggingInfo("Clicking to shown users available on the project");
        controlPanelService.clickToShowProjectUsers();
        logManager.loggingInfo("Getting text from 'No projects available' message");
        String noProjectText = controlPanelService.getTextFromNoProjectAvailableMessage();
        logManager.loggingInfo("Checking projects members are not available");
        Assert.assertEquals(noProjectText,"You have no project team members to be reviewed","There was an error, the text has been changed or projects stay available for invalid time period");
}

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on User's list" +
            "\n 3. Click on any user from the list" +
            "\n 4. Choose Month view on Control panel" +
            "\n 5. Click to return to current User's page on the Warning message" +
            "\n 6. Refresh the page" +
            "\n 7. Verify the Month view is preserved")
    public void checkMonthViewIsPreserved(){
        logManager.loggingInfo("Clicking to shown users available on the project");
        controlPanelService.clickToShowProjectUsers();
        logManager.loggingInfo("Clicking the first user on the list");
        controlPanelService.FirsUserOnTheListClick();
        logManager.loggingInfo("Opening Month view");
        controlPanelService.monthViewClick();
        logManager.loggingInfo("Getting text from the time range");
        String verifyActiveRangeBeforeSwitch = controlPanelService.getTextFromSelectedRangeView();
        logManager.loggingInfo("Clicking to return to the current user's page on Warning message");
        warningMessagesService.clickToReturnToCurrentUserPage();
        logManager.loggingInfo("Getting text from the time range");
        String verifyActiveRangeAfterSwitch = controlPanelService.getTextFromSelectedRangeView();
        logManager.loggingInfo("Checking month view is preserved");
        Assert.assertEquals(verifyActiveRangeBeforeSwitch, verifyActiveRangeAfterSwitch, "Month view is not preserved");
    }
}
