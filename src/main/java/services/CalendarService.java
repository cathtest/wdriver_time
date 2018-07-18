package services;

import blocks.Calendar;
import unit.TestCase;

public class CalendarService {

    private Calendar calendar;

    public CalendarService() {
        this.calendar = new Calendar(TestCase.driver);
    }

    private Calendar getCalendar(){
        return calendar;
    }

    public String getTextFromCalendarButton(){
       return calendar.getCalendarButton().getText();
    }

    public void calendarButtonClick(){
        calendar.getCalendarButton().click();
    }

    public void clickNotCurrentTimeRange(){
        calendar.getTimeRangeNotCurrent().click();
    }

    public void openLastYearOnTheList(){
        calendar.getLastYear().click();
    }

    public void FollowingYearClick(){
        calendar.getTheFollowingYear().click();
    }

    public void firstMonthInTheYearClick(){
        calendar.getTheFirstMonth().click();
    }

    public void firstDayInTheMonthClick(){
        calendar.getTheFirstDay().click();
    }
}
