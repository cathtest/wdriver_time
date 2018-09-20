package com.kate.mentoring.test;

import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.unit.TestCase;
import com.kate.mentoring.java.utils.KathyLog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExcel extends TestCase{

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Export to Excel" +
            "\n 3. Click on Export button" +
            "\n 5. Compare the project names")
    public void exportExcel() {
        KathyLog.info("Getting current URL");
        String expectedURL = DriverManager.getInstance().getDriver().getCurrentUrl();

        KathyLog.info("Clicking Export to Excel button");
        controlPanelService.exportToExcelButtonClick();

        KathyLog.info("Clicking Export button");
        exportExcelService.exportButtonClick();

        KathyLog.debug("Getting current URL");
        String redirectURL = DriverManager.getInstance().getDriver().getCurrentUrl();

        KathyLog.debug("Checking export to Excel");
        Assert.assertEquals(redirectURL, expectedURL, "Export button is not available");
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
        KathyLog.info("Clicking Export to Excel button");
        controlPanelService.exportToExcelButtonClick();

        KathyLog.info("Clicking on the right calendar");
        exportExcelService.rightCalendarClick();

        KathyLog.info("Opening Month view");
        calendarLogics.openMonthView();

        KathyLog.info("Opening Year view");
        calendarLogics.openYearView();

        KathyLog.info("Opening the last year on the list");
        calendarService.openLastYearOnTheList();

        KathyLog.info("Opening the first month in the year");
        calendarService.firstMonthInTheYearClick();

        KathyLog.info("Choosing the first day in the month");
        calendarService.firstDayInTheMonthClick();

        KathyLog.debug("Getting current URL");
        String expectedURL = DriverManager.getInstance().getDriver().getCurrentUrl();

        KathyLog.info("Clicking Export button");
        exportExcelService.exportButtonClick();


        KathyLog.debug("Verifying that the URLs got before and after clicking Export button are equal");
        Assert.assertEquals(DriverManager.getInstance().getDriver().getCurrentUrl(), expectedURL, "There was an error: export is executed with the time range that is more than year");

        KathyLog.debug("Checking whether Export button is enabled");
        Assert.assertFalse(exportExcelService.checkExportButtonIsEnabled(), "There is an issue, button is enabled");

        KathyLog.debug("Checking whether Export block is shown itself");
        Assert.assertTrue(exportExcelService.checkExportBlockIsDisplayed(), "There was an error, excel block is not shown!");
    }
}
