package com.kate.mentoring.test;

import com.kate.mentoring.java.unit.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.List;

public class TestTimeRange extends TestCase{

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. Click on the user selector" +
            "\n 4. For each user: " +
            "\n 4.1 open user profile" +
            "\n 4.2 get user total reported time")
    public void checkAllUsersAreAvailableAndTheirTotalHours() {
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
            List<TextBlock> listOfTeamMembers = controlPanelService.getListOfTeamMembers();

            listOfTeamMembers.forEach(member->{
                logManager.loggingInfo("Checking certain user has reported his time");

                Assert.assertTrue(totalHoursForUser.matches(regEx), logManager.loggingSevere("UserModel hasn't filled his time")); }
            );
        }
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

        Assert.assertSame(totalTimeBeforeChanges, totalTimeAfterChanges, logManager.loggingSevere("Time range hasn't been changed"));
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

        Assert.assertEquals(dateBeforeSwitch, dateAfterSwitch, logManager.loggingSevere("There was an error, date hasn't been changed"));
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

        Assert.assertNotSame(columnBeforeSwitch, columnAfterSwitch, logManager.loggingSevere("There was an error, date hasn't been changed"));
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

        Assert.assertNotEquals(dateBeforeYearSwitch, dateAfterYearSwitch, logManager.loggingSevere("Date is not preserved"));
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
            "\n 9. Click on UserModel's list" +
            "\n 10. Verify 'You have no project team members to be reviewed' text is displayed")
    public void checkThereIsNoProjectAvailableForInvalidTimePeriod() {
        logManager.loggingInfo("Clicking Calendar button");
        calendarService.calendarButtonClick();

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

        logManager.loggingInfo("Clicking to show users available on the project");
        controlPanelService.clickToShowProjectUsers();

        logManager.loggingInfo("Getting text from 'No projects available' message");
        String noProjectText = controlPanelService.getTextFromNoProjectAvailableMessage();
        controlPanelService.noProjectAvailableHighlightUnhighlight();

        logManager.loggingInfo("Checking projects members are not available");

        Assert.assertEquals(noProjectText,"You have no project team members to be reviewed",logManager.loggingSevere("There was an error, the text has been changed or projects stay available for invalid time period"));
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on UserModel's list" +
            "\n 3. Click on any user from the list" +
            "\n 4. Choose Month view on Control panel" +
            "\n 5. Click to return to current UserModel's page on the Warning message" +
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

        Assert.assertEquals(verifyActiveRangeBeforeSwitch, verifyActiveRangeAfterSwitch, logManager.loggingSevere("Month view is not preserved"));
    }

}
