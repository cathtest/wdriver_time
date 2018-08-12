package services;

import blocks.Calendar;
import logics.ClickImplementation;
import wrapped.DateEntity;

import java.util.List;

public class CalendarService {

    private Calendar calendar;
    ClickImplementation clickImplementation;


    public CalendarService() {
        this.calendar = new Calendar();
        this.clickImplementation = new ClickImplementation();
    }


    public String getTextFromCalendarButton(){
        return calendar.getCalendarButton().getText();
    }

    public void calendarButtonClick(){
        clickImplementation.click(calendar.getCalendarButton());
    }

    public void clickNotCurrentTimeRange(){
        clickImplementation.click(calendar.getTimeRangeNotCurrent());
    }

    public void openLastYearOnTheList(){
        clickImplementation.click(calendar.getLastYear());
    }

    public void FollowingYearClick(){
        clickImplementation.click(calendar.getTheFollowingYear());
    }

    public void firstMonthInTheYearClick(){
        clickImplementation.click(calendar.getTheFirstMonth());
    }

    public void firstDayInTheMonthClick(){
        clickImplementation.click(calendar.getTheFirstDay());
    }

    public void changeRange(){
        clickImplementation.click(calendar.getAnotherView());
    }

    public String getTextFromRange(){
        return calendar.getAnotherView().getText();
    }

    public void rightArrowClick(){
        clickImplementation.click(calendar.getRightArrow());
    }

    public void leftArrowClick(){
        clickImplementation.click(calendar.getLeftArrow());
    }

    public List<DateEntity> getListOfYears(){
        return calendar.getYearsList();
    }

}
