import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.LogManager;
import utils.Sleeper;
import unit.TestCase;
import java.util.List;

public class TestClass extends TestCase {

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day enter the quantity of hours")
    public void checkAddHoursWorkCorrectly() {
        tableJournalService.clickOnTheFirstActivity();
        tableJournalService.sendKeysToTheActivity("Daily sync-up");
        tableJournalService.fillCells("0.5");
        LogManager.info("Checking how the cells are filled with hours");
        Assert.assertTrue(controlPanelBlock.getCellFilled().isDisplayed(), "The field is not filled");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on projects drop-down list" +
            "\n 3. Get text from the first project (except 'All')" +
            "\n 4. Get text from the first project in the table" +
            "\n 5. Compare the project names" +
            "\n 6. Do the same for the rest of the projects")
    public void checkProjectsInProjectListAreShownOnTheDashboard() {
        controlPanelService.chooseProject();
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
        controlPanelService.monthViewClick();
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
        controlPanelService.monthViewClick();
        String totalTimeBeforeChanges = tableJournalService.getTextFromTotalHours();
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalService.sendKeysToTheActivity("Some test activity");
        tableJournalService.logHoursForToday("1");
        controlPanelService.clickSaveHoursButton();
        String totalTimeAfterChanges = tableJournalService.getTextFromTotalHours();
        LogManager.info("Checking that the total reported time has been changed after logging some new hours");
        Assert.assertFalse(totalTimeBeforeChanges == totalTimeAfterChanges);
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. Click on the user selector" +
            "\n 4. For each user: " +
            "\n 4.1 open user profile" +
            "\n 4.2 get user total reported time")
    public void checkAllUsersAreAvailableAndThersTotalHours() {
        controlPanelService.monthViewClick();
        controlPanelBlock.getUserSelectorButton().click();
        int QuantityOfTeamMembers = 89;
        String regEx = "^Total\\s((152)\\W)(?=152)|((160)\\W)(?=160)|((168)\\W)(?=168)";
        for (int i = 0; i < QuantityOfTeamMembers; i++) {
            controlPanelService.clickTeamMemberWithIndex(i);
            String totalHoursForUser = tableJournalService.getTextFromTotalHours();
            controlPanelService.clickToShowProjectUsers();
            List<WebElement> list = controlPanelService.getListOfTeamMembers();

            for (WebElement element : list) {
                LogManager.info("Checking certain user has reported his time");
                Assert.assertTrue(totalHoursForUser.matches(regEx), "User hasn't filled his time");
            }
        }
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Export to Excel" +
            "\n 3. Click on Export button" +
            "\n 5. Compare the project names")
    public void exportExcel() {
        String expectedURL = driver.getCurrentUrl();
        controlPanelService.exportToExcelButtonClick();
        exportExcelService.exportButtonClick();
        String redirectURL = driver.getCurrentUrl();
        Assert.assertEquals(redirectURL, expectedURL);
        LogManager.info("Checking export to Excel");
        Assert.assertEquals(redirectURL, expectedURL, "Export button is not available");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on 2 weeks button" +
            "\n 3. Verify total time is changed")
    public void checkTwoWeeksButtonWorksCorrectly() {
        String totalTimeBeforeChanges = tableJournalService.getTextFromTotalHours();
        controlPanelService.clickTwoWeeksPeriodButton();
        String totalTimeAfterChanges = tableJournalService.getTextFromTotalHours();
        LogManager.info("Checking time range has been changed");
        Assert.assertFalse(totalTimeBeforeChanges == totalTimeAfterChanges);
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click the Current User button at the top left corner" +
            "\n 3. Click search button" +
            "\n 4. Enter existing user name" +
            "\n 5. Verify User is found")
    public void checkUserSearchIsCorrect() {
        String someName = "Alexey Alexandrov";
        controlPanelService.clickToShowProjectUsers();
        controlPanelService.clickSearchButton();
        controlPanelService.sendValueToSearch(someName);
        Sleeper.sleep(2);
        String resultSearchName = controlPanelService.getTextFromUserFound();
        LogManager.info("Checking search works correclty");
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
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalService.sendKeysToTheActivity("Another test activity");
        tableJournalService.fillCells("0.5");
        driver.navigate().refresh();
        LogManager.info("Checking data is not saved after refreshing the page");
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
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalService.sendKeysToTheActivity("Another one test activity");
        tableJournalService.fillCells("0.5");
        controlPanelService.cancelButtonClick();
        LogManager.info("Checking data is not saved after clicking Cancel button");
        Assert.assertTrue(tableJournalService.checkWhetherWorkingDayListIsEmpty(), "There was an error, the field stays filled");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Click today button" +
            "\n 5. Verify date on the calendar button stays the same as it was at the beginning")
    public void checkTodayButtonWorksCorrectly() {
        String dateBeforeSwitch = calendarService.getTextFromCalendarButton();
        calendarService.calendarButtonClick();
        calendarService.clickNotCurrentTimeRange();
        controlPanelService.TodatButtonClick();
        String dateAfterSwitch = calendarService.getTextFromCalendarButton();
        LogManager.info("Checking time range is changed");
        Assert.assertTrue(dateAfterSwitch.equals(dateBeforeSwitch), "There was an error, date hasn't been changed");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Verify date of the columns in the table has been changed")
    public void checkTableIsShownAccordingToCalendarWeek() {
        String columnBeforeSwitch = tableJournalService.getTextFromWorkingDayColumnDate();
        calendarService.calendarButtonClick();
        calendarService.clickNotCurrentTimeRange();
        String columnAfterSwitch = tableJournalService.getTextFromWorkingDayColumnDate();
        LogManager.info("Checking the column has been changed");
        Assert.assertFalse(columnBeforeSwitch == columnAfterSwitch, "There was an error, date hasn't been changed");
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
        String dateBeforeYearSwitch = calendarService.getTextFromCalendarButton();
        calendarService.calendarButtonClick();
        calendarLogics.openMonthView();
        calendarLogics.openYearView();
        calendarService.FollowingYearClick();
        calendarService.firstMonthInTheYearClick();
        calendarService.clickNotCurrentTimeRange();
        controlPanelService.monthViewClick();
        controlPanelService.weekViewClick();
        String dateAfterYearSwitch = calendarService.getTextFromCalendarButton();
        LogManager.info("Verifying that time range is shown in accordance with the year and month chosen on Calendar");
        Assert.assertNotEquals(dateBeforeYearSwitch, dateAfterYearSwitch, "Date is not preserved");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Add certain activity" +
            "\n 3. Add hours for that activity" +
            "\n 4. Click Save button" +
            "\n 5. Add activity with the same name" +
            "\n 6. Click to add hours for that activity" +
            "\n 7. Close warning message" +
            "\n 8. Click to add hours for that activity")
    public void checkTwoEqualActivitiesCannotBeAdded() {
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalService.sendKeysToTheActivity("Ordinary test activity");
        tableJournalService.fillCells("0.5");
        controlPanelService.clickSaveHoursButton();
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalService.sendKeysToTheActivity("Ordinary test activity");
        try {
            tableJournalService.EmptyCellClick();
            tableJournalService.sendHoursToEmptyCell("0.5");
            warningMessagesService.clickToCloseWarningMesssage();
            tableJournalService.EmptyCellClick();
            tableJournalService.sendHoursToEmptyCell("0.5");
        } catch (WebDriverException we) {
            LogManager.info("WebDriverException has been detected");
        }
        LogManager.info("Checking that the hours can't be added to the activity with the same name");
        Assert.assertEquals(tableJournalBlock.getEmptyCell().getText(), "", "There was an error, the hours for the Activity with the same name have been added");
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
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalService.sendKeysToTheActivity("Regular test activity");
        tableJournalService.fillCells("0.5");
        controlPanelService.clickSaveHoursButton();
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalService.overtimeModeSwitchOn();
        tableJournalService.overtimeNotEnabledCellClick();
        controlPanelService.clickSaveHoursButton();
        tableJournalService.overTimeEnabledClick();
        overtimeCancellingService.cancelOvertimeButtonClick();
        controlPanelBlock.getSaveHoursButton().click();
        driver.navigate().refresh();
        Sleeper.sleep(2);
        LogManager.info("Checking that Overtime cells can be cancelled");
        Assert.assertFalse(tableJournalBlock.getOvertimeSubmitedCell().isDisplayed(), "Overtimes cannot be cancelled");
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
        tableJournalService.clickActivityButtonWithIndex(0);
        tableJournalBlock.getActivityField().sendKeys("Next activity");
        tableJournalService.fillCells("0.5");
        controlPanelService.clickSaveHoursButton();
        tableJournalService.overtimeModeSwitchOn();
        Sleeper.sleep(2);
        tableJournalService.overtimeNotEnabledCellClick();
        controlPanelService.clickSaveHoursButton();
        String overtimeCheck = tableJournalService.getTextFromTotalOverTimeInfo();
        String regex = "^Overtime\\s([0-9]\\W[0-9])|([0-9])|([0-9][0-9])";
        LogManager.info("Checking Overtime hours are saved");
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
        controlPanelService.exportToExcelButtonClick();
        exportExcelService.rightCalendarClick();
        calendarLogics.openMonthView();
        calendarLogics.openYearView();
        calendarService.openLastYearOnTheList();
        calendarService.firstMonthInTheYearClick();
        calendarService.firstDayInTheMonthClick();
        String expectedURL = driver.getCurrentUrl();
        exportExcelService.exportButtonClick();
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "There was an error: export is executed with the time range that is more than year");
        Assert.assertFalse(exportExcelService.checkExportButtonIsEnabled(), "There is an issue, button is enabled");
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
        calendarService.calendarButtonClick();
        calendarLogics.openMonthView();
        calendarLogics.openYearView();
        calendarLogics.openYear(1998);
        calendarService.firstMonthInTheYearClick();
        calendarService.clickNotCurrentTimeRange();
        controlPanelService.clickToShowProjectUsers();
        String noProjectText = controlPanelService.getTextFromNoProjectAvailableMessage();
        LogManager.info("Checking projects members are not available");
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
        controlPanelService.clickToShowProjectUsers();
        controlPanelService.FirsUserOnTheListClick();
        controlPanelService.monthViewClick();
        String verifyActiveRangeBeforeSwitch = controlPanelBlock.getRangeViewSelected().getText();
        warningMessagesService.clickToReturnToCurrentUserPage();
        String verifyActiveRangeAfterSwitch = controlPanelService.getTextFromSelectedRangeView();
        LogManager.info("Checking month view is preserved");
        Assert.assertEquals(verifyActiveRangeBeforeSwitch, verifyActiveRangeAfterSwitch, "Month view is not preserved");
    }
}
