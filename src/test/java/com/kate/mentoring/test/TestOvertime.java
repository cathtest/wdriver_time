package com.kate.mentoring.test;

import com.kate.mentoring.java.enums.ActivityNames;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.unit.TestCase;
import com.kate.mentoring.java.utils.KathyLog;
import com.kate.mentoring.java.utils.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.kate.mentoring.java.enums.ActivityNames.ENVIRONMENT_SETUP;

public class TestOvertime extends TestCase{

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
        KathyLog.info("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);

        KathyLog.info("Sending keys to the activity");
        fillingActivityService.fill(ENVIRONMENT_SETUP.getValue());

        KathyLog.info("Filling cells with hours");
        quantity.fill("1");

        KathyLog.info("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();

        KathyLog.info("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);

        KathyLog.info("Switching on Overtime mode");
        tableJournalService.overtimeModeSwitchOn();

        KathyLog.info("Clicking on the cell where Overtime is not enabled");
        tableJournalService.overtimeNotEnabledCellClick();

        KathyLog.info("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();

        KathyLog.info("Clicking on the cell where Overtime has already been enabled");
        tableJournalService.overTimeEnabledClick();

        KathyLog.info("Clicking Cancel button on Overtime page");
        overtimeCancellingService.cancelOvertimeButtonClick();

        KathyLog.info("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();

        KathyLog.info("Refreshing the page");
        DriverManager.getInstance().getDriver().navigate().refresh();
        Sleeper.sleep(2);

        KathyLog.debug("Checking that Overtime cells can be cancelled");

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
        KathyLog.info("Clicking activity button");
        tableJournalService.clickActivityButtonWithIndex(0);

        KathyLog.info("Sending keys to the activity");
        fillingActivityService.fill(ActivityNames.CHARLES.getValue());

        KathyLog.info("Filling cells with hours");
        quantity.fill("1");

        KathyLog.info("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();

        KathyLog.info("Switching on Overtime mode");
        tableJournalService.overtimeModeSwitchOn();
        Sleeper.sleep(2);

        KathyLog.info("Clicking on the cell where Overtime is not enabled");
        tableJournalService.overtimeNotEnabledCellClick();

        KathyLog.info("Clicking Save hours button");
        controlPanelService.clickSaveHoursButton();

        KathyLog.debug("Getting text from Overtime Info element");
        String overtimeCheck = tableJournalService.getTextFromTotalOverTimeInfo();
        String regex = "^Overtime\\s([0-9]\\W[0-9])|([0-9])|([0-9][0-9])";

        KathyLog.debug("Checking Overtime hours are saved");
        KathyLog.warn("In case overtime hours are added, it could be problematical to cancel them");

        Assert.assertTrue(overtimeCheck.matches(regex), "Overtime hours are not saved");
    }
}
