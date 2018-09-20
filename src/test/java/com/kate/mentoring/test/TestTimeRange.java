package com.kate.mentoring.test;

import com.kate.mentoring.java.unit.TestCase;
import com.kate.mentoring.java.utils.KathyLog;
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
        KathyLog.info("Clicking to open Month view");
        controlPanelService.monthViewClick();

        KathyLog.info("Opening the list of users available on the project");
        controlPanelService.clickToShowProjectUsers();

        KathyLog.debug("Getting size of team members");
        int quantityOfTeamMembers = controlPanelService.sizeOfTeamMembersList();
        String regEx = "^Total\\s((152)\\W)(?=152)|((160)\\W)(?=160)|((168)\\W)(?=168)";

        for (int i = 0; i < quantityOfTeamMembers; i++) {
            KathyLog.info("Clicking on the user with index");
            controlPanelService.clickTeamMemberWithIndex(i);

            KathyLog.debug("Getting text from total hours element");
            String totalHoursForUser = tableJournalService.getTextFromTotalHours();

            KathyLog.info("Opening the list of users available on the project");
            controlPanelService.clickToShowProjectUsers();

            KathyLog.debug("Getting list of all the users on the project");
            List<TextBlock> listOfTeamMembers = controlPanelService.getListOfTeamMembers();

            listOfTeamMembers.forEach(member->{
                KathyLog.debug("Checking certain user has reported his time");
                KathyLog.warn("If one of the users hasn't filled his time journal, the test will be failed and other users won't be check");

                Assert.assertTrue(totalHoursForUser.matches(regEx), "User hasn't filled his time"); }
            );
        }
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on 2 weeks button" +
            "\n 3. Verify total time is changed")
    public void checkTwoWeeksButtonWorksCorrectly() {
        KathyLog.debug("Getting text from total hours element");
        String totalTimeBeforeChanges = tableJournalService.getTextFromTotalHours();

        KathyLog.info("Clicking Two Weeks period");
        controlPanelService.clickTwoWeeksPeriodButton();

        KathyLog.debug("Getting text from total hours element");
        String totalTimeAfterChanges = tableJournalService.getTextFromTotalHours();

        KathyLog.debug("Checking time range has been changed");

        Assert.assertSame(totalTimeBeforeChanges, totalTimeAfterChanges, "Time range hasn't been changed");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Click today button" +
            "\n 5. Verify date on the calendar button stays the same as it was at the beginning")
    public void checkTodayButtonWorksCorrectly() {
        KathyLog.debug("Getting text from Calendar button");
        String dateBeforeSwitch = calendarService.getTextFromCalendarButton();

        KathyLog.info("Clicking Calendar button");
        calendarService.calendarButtonClick();

        KathyLog.info("Clicking time range that is not current");
        calendarService.clickNotCurrentTimeRange();

        KathyLog.info("Clicking today button");
        controlPanelService.todayButtonClick();

        KathyLog.debug("Getting text from Calendar button");
        String dateAfterSwitch = calendarService.getTextFromCalendarButton();

        KathyLog.debug("Checking time range is changed");

        Assert.assertEquals(dateBeforeSwitch, dateAfterSwitch, "There was an error, date hasn't been changed");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Verify date of the columns in the table has been changed")
    public void checkTableIsShownAccordingToCalendarWeek() {
        KathyLog.debug("Getting text from the column of the working day");
        String columnBeforeSwitch = tableJournalService.getTextFromWorkingDayColumnDate();

        KathyLog.info("Clicking Calendar button");
        calendarService.calendarButtonClick();

        KathyLog.info("Clicking time range that is not current");
        calendarService.clickNotCurrentTimeRange();

        KathyLog.debug("Getting text from the column of the working day");
        String columnAfterSwitch = tableJournalService.getTextFromWorkingDayColumnDate();

        KathyLog.debug("Checking the column has been changed");

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
        KathyLog.debug("Getting text from Calendar button");
        String dateBeforeYearSwitch = calendarService.getTextFromCalendarButton();

        KathyLog.info("Clicking Calendar button");
        calendarService.calendarButtonClick();

        KathyLog.info("Clicking Month view");
        calendarLogics.openMonthView();

        KathyLog.info("Clicking Year view");
        calendarLogics.openYearView();

        KathyLog.info("Choosing the following year");
        calendarService.FollowingYearClick();

        KathyLog.info("Choosing the first month in the year");
        calendarService.firstMonthInTheYearClick();

        KathyLog.info("Clicking time range that is not current");
        calendarService.clickNotCurrentTimeRange();

        KathyLog.info("Clicking Month view");
        controlPanelService.monthViewClick();

        KathyLog.info("Clicking Week view");
        controlPanelService.weekViewClick();

        KathyLog.debug("Getting text from Calendar button");
        String dateAfterYearSwitch = calendarService.getTextFromCalendarButton();

        KathyLog.debug("Verifying that time range is shown in accordance with the year and month chosen on Calendar");

        Assert.assertNotEquals(dateBeforeYearSwitch, dateAfterYearSwitch, "Date is not preserved");
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
        KathyLog.info("Clicking Calendar button");
        calendarService.calendarButtonClick();

        KathyLog.info("Opening Month view");
        calendarLogics.openMonthView();

        KathyLog.info("Opening Year view");
        calendarLogics.openYearView();

        KathyLog.info("Opening the year chosen");
        calendarLogics.openYear(1998);

        KathyLog.info("Opening the first month in the year");
        calendarService.firstMonthInTheYearClick();

        KathyLog.info("Opening the time range that is not current");
        calendarService.clickNotCurrentTimeRange();

        KathyLog.info("Clicking to show users available on the project");
        controlPanelService.clickToShowProjectUsers();

        KathyLog.debug("Getting text from 'No projects available' message");
        String noProjectText = controlPanelService.getTextFromNoProjectAvailableMessage();

        KathyLog.debug("Checking projects members are not available");

        Assert.assertEquals(noProjectText,"You have no project team members to be reviewed", "There was an error, the text has been changed or projects stay available for invalid time period");
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
        KathyLog.info("Clicking to shown users available on the project");
        controlPanelService.clickToShowProjectUsers();

        KathyLog.info("Clicking the first user on the list");
        controlPanelService.FirsUserOnTheListClick();

        KathyLog.info("Opening Month view");
        controlPanelService.monthViewClick();

        KathyLog.debug("Getting text from the time range");
        String verifyActiveRangeBeforeSwitch = controlPanelService.getTextFromSelectedRangeView();

        KathyLog.info("Clicking to return to the current user's page on Warning message");
        warningMessagesService.clickToReturnToCurrentUserPage();

        KathyLog.debug("Getting text from the time range");
        String verifyActiveRangeAfterSwitch = controlPanelService.getTextFromSelectedRangeView();

        KathyLog.debug("Checking month view is preserved");

        Assert.assertEquals(verifyActiveRangeBeforeSwitch, verifyActiveRangeAfterSwitch, "Month view is not preserved");
    }
}
