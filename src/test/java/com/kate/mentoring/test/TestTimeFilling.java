package com.kate.mentoring.test;

import com.kate.mentoring.java.enums.ActivityNames;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.unit.TestCase;
import com.kate.mentoring.java.utils.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.kate.mentoring.java.enums.ActivityNames.*;

public class TestTimeFilling extends TestCase {

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. For each working day enter the quantity of hours")
    public void checkAddHoursWorkCorrectly() {
        logManager.loggingInfo("Clicking on the first activity");
        tableJournalService.clickOnTheFirstActivity();

        logManager.loggingInfo("Sending keys to the activity");
        fillingActivityService.fill(REQUIREMENTS.getValue());

        logManager.loggingInfo("Filling cells");
        quantity.fill("1");

        logManager.loggingInfo("Checking how the cells are filled with hours and the activity field - with the name of the actvity");

        Assert.assertTrue(controlPanelService.checkWhetherCellIsFilled(), logManager.loggingSevere("The field is not filled"));
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

        Assert.assertTrue(totalForCurrentUser.matches(regEx), logManager.loggingSevere("You haven't filled all the hours"));
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
        fillingActivityService.fill(TEST.getValue());

        logManager.loggingInfo("Logging hours for today");
        tableJournalService.logHoursForToday("1");

        logManager.loggingInfo("Saving hours");
        controlPanelService.clickSaveHoursButton();

        logManager.loggingInfo("Getting text from total hours element");
        String totalTimeAfterChanges = tableJournalService.getTextFromTotalHours();

        logManager.loggingInfo("Checking that the total reported time has been changed after logging some new hours");

        Assert.assertNotSame(totalTimeBeforeChanges, totalTimeAfterChanges, logManager.loggingSevere("Verifying time before and after change"));
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
        fillingActivityService.fill(BUGS_LOGGING.getValue());

        logManager.loggingInfo("Filling cells with hours");
        quantity.fill("1");

        logManager.loggingInfo("Refreshing the page");
        DriverManager.getInstance().getDriver().navigate().refresh();

        logManager.loggingInfo("Checking data is not saved after refreshing the page");

        Assert.assertTrue(tableJournalService.checkWhetherWorkingDayListIsEmpty(), logManager.loggingSevere("There was an error, the field stays filled"));
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
        fillingActivityService.fill(DAILY_MEET.getValue());

        logManager.loggingInfo("Filling cells with hours");
        quantity.fill("1");

        logManager.loggingInfo("Clicking Cancel button");
        controlPanelService.cancelButtonClick();

        logManager.loggingInfo("Checking data is not saved after clicking Cancel button");

        Assert.assertTrue(tableJournalService.checkWhetherWorkingDayListIsEmpty(), logManager.loggingSevere("There was an error, the field stays filled"));
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
        fillingActivityService.fill(ActivityNames.COMMUNICATION.getValue());

        logManager.loggingInfo("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);

        logManager.loggingInfo("Sending the same keys to the newly created activity");
        fillingActivityService.fill(ActivityNames.COMMUNICATION.getValue());

        logManager.loggingInfo("Clicking Save button");
        controlPanelService.clickSaveHoursButton();

        logManager.loggingInfo("Closing warning message");
        warningMessagesService.clickToCloseWarningMesssage();

        logManager.loggingInfo("Clicking Save button");
        controlPanelService.clickSaveHoursButton();

        logManager.loggingInfo("Checking that the hours can't be added to the activity with the same name");

        Assert.assertTrue(warningMessagesService.checkWarningMessageIsDisplayed(), logManager.loggingSevere("There was an error, the hours for the Activity with the same name have been added"));
    }

    @Test(description=
            "\n 1. Open time.epam.com" +
            "\n 2. Add some activity"+
            "\n 3. Fill cells with 25 hours (per each)" +
            "\n 4. Click Save" +
            "\n 5. Verify Warning block appears")
    public void checkItIsForbiddenToAddMoreThanTwentyFourHoursPerDay(){
        logManager.loggingInfo("Clicking to add Activity");
        tableJournalService.clickActivityButtonWithIndex(0);

        logManager.loggingInfo("Filling Activity cell");
        fillingActivityService.fill(ActivityNames.COMMUNICATION.getValue());


        logManager.loggingInfo("Filing all the working day cells with 25 hours");
        quantity.fill("25");
        Sleeper.sleep(1);

        logManager.loggingInfo("Clicking to save hours");
        controlPanelService.clickSaveHoursButton();

        Assert.assertTrue(warningMessagesService.checkModalWindowIsDisplayed(), logManager.loggingSevere("Warning message is not displayed, UserModel can save more than 25 hours a day"));
    }
}
