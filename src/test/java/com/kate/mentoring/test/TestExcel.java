package com.kate.mentoring.test;

import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.unit.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExcel extends TestCase{

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
        Assert.assertEquals(redirectURL, expectedURL, logManager.loggingSevere("Export button is not available"));
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
        Assert.assertTrue(exportExcelService.checkExportBlockIsDisplayed(), logManager.loggingSevere("There was an error, excel block is not shown!"));
    }
}
