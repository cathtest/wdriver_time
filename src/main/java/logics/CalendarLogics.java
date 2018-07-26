package logics;

import org.openqa.selenium.StaleElementReferenceException;
import services.CalendarService;
import utils.LogManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarLogics {

    LogManager logManager = new LogManager();

    private CalendarService calendarService = new CalendarService();

    public CalendarLogics() {
        this.calendarService = new CalendarService();
    }

    public void openYearView(){
        calendarService.changeRange();
    }

    public void openMonthView(){
        calendarService.changeRange();
    }

    public void openYear(int yearChosen) {
        String textFromPeriod = calendarService.getTextFromRange();
        String startYearStr = textFromPeriod.substring(0, 4);
        String endYearStr = textFromPeriod.substring(5, 11);
        int startYearInt = extractInt(startYearStr);
        int endYearInt = extractInt(endYearStr);
        logManager.loggingInfo("Searching for the year chosen\n");

        if (startYearInt < yearChosen && yearChosen > endYearInt) {
            for (int i = yearChosen; startYearInt < i && i > endYearInt; i++) {
                calendarService.rightArrowClick();
                calendarService.rightArrowHighlight();
                calendarService.rightArrowUnhighlight();
                textFromPeriod = calendarService.getTextFromRange();
                startYearStr = textFromPeriod.substring(0, 4);
                endYearStr = textFromPeriod.substring(5, 11);
                startYearInt = extractInt(startYearStr);
                endYearInt = extractInt(endYearStr);
                logManager.loggingInfo(startYearInt + " " + endYearInt);
            }
            clickOnYear(yearChosen);
        } else if (startYearInt > yearChosen && yearChosen < endYearInt) {
            for (int i = yearChosen; startYearInt > i && i < endYearInt; i++) {
                calendarService.leftArrowClick();
                calendarService.leftArrowHighlight();
                calendarService.leftArrowUnhighlight();
                textFromPeriod = calendarService.getTextFromRange();
                startYearStr = textFromPeriod.substring(0, 4);
                endYearStr = textFromPeriod.substring(5, 11);
                startYearInt = extractInt(startYearStr);
                endYearInt = extractInt(endYearStr);
            }
            clickOnYear(yearChosen);
        }
    }

    public void clickOnYear(int yearChosen){
        try {
            calendarService.getListOfYears().forEach(year -> {
                int a = extractInt(year.getText());
                if (a == yearChosen) {
                    year.click();
                }
            });
        } catch (StaleElementReferenceException ex) {

        }

    }
    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }
}
