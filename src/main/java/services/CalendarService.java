package services;

import blocks.Calendar;
import org.openqa.selenium.WebElement;
import unit.TestCase;

import java.util.List;

public class CalendarService {

    private Calendar calendar;

    public CalendarService() {
        this.calendar = new Calendar();
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

    public void changeRange(){
        calendar.getAnotherView().click();
    }

    public String getTextFromRange(){
        return calendar.getAnotherView().getText();
    }
    public void rightArrowClick(){
        calendar.getRightArrow().click();
    }

    public void leftArrowClick(){
        calendar.getLeftArrow().click();
    }

    public List<WebElement> getListOfYears(){
        return calendar.getYearsList();
    }

    public void calendarButtonHighlight(){
        calendar.highlightElement(calendar.getCalendarButton());
    }
    public void calendarButtonUnhighlight(){
        calendar.unHighlightElement(calendar.getCalendarButton());
    }

    public void changeRangeHighlight(){
        calendar.highlightElement(calendar.getAnotherView());
    }

    public void changeRangeUnhighlight(){
        calendar.unHighlightElement(calendar.getAnotherView());
    }

    public void leftArrowHighlight(){
        calendar.highlightElement(calendar.getLeftArrow());
    }

    public void leftArrowUnhighlight(){
        calendar.unHighlightElement(calendar.getLeftArrow());
    }

    public void rightArrowHighlight(){
        calendar.highlightElement(calendar.getRightArrow());
    }

    public void rightArrowUnhighlight(){
        calendar.unHighlightElement(calendar.getRightArrow());
    }

    public void firstMonthInTheYearHighlight(){
        calendar.highlightElement(calendar.getTheFirstMonth());
    }

    public void firstMonthInTheYearUnhighlight(){
        calendar.unHighlightElement(calendar.getTheFirstMonth());
    }

    public void notCurrentTimeRangeHighlight(){
        calendar.highlightElement(calendar.getTimeRangeNotCurrent());
    }

    public void notCurrentTimeRangeUnhighlight(){
        calendar.unHighlightElement(calendar.getTimeRangeNotCurrent());
    }


}
