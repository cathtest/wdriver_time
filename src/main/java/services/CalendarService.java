package services;

import blocks.Calendar;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import unit.TestCase;

import java.util.List;

public class CalendarService {

    private Calendar calendar;
    private HighlightService highlightService;

    public CalendarService() {
        this.calendar = new Calendar();
        this.highlightService = new HighlightService();
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

    public List<TextBlock> getListOfYears(){
        return calendar.getYearsList();
    }

    public void calendarButtonHighlight(){
        highlightService.highlightElement(calendar.getCalendarButton());
    }

    public void calendarButtonUnhighlight(){
        highlightService.unHighlightElement(calendar.getCalendarButton());
    }

    public void changeRangeHighlight(){
        highlightService.highlightElement(calendar.getAnotherView());
    }

    public void changeRangeUnhighlight(){
        highlightService.unHighlightElement(calendar.getAnotherView());
    }

    public void leftArrowHighlight(){
        highlightService.highlightElement(calendar.getLeftArrow());
    }

    public void leftArrowUnhighlight(){
        highlightService.unHighlightElement(calendar.getLeftArrow());
    }

    public void rightArrowHighlight(){
        highlightService.highlightElement(calendar.getRightArrow());
    }

    public void rightArrowUnhighlight(){
        highlightService.unHighlightElement(calendar.getRightArrow());
    }

    public void firstMonthInTheYearHighlight(){
        highlightService.highlightElement(calendar.getTheFirstMonth());
    }

    public void firstMonthInTheYearUnhighlight(){
        highlightService.unHighlightElement(calendar.getTheFirstMonth());
    }

    public void notCurrentTimeRangeHighlight(){
        highlightService.highlightElement(calendar.getTimeRangeNotCurrent());
    }

    public void notCurrentTimeRangeUnhighlight(){
        highlightService.unHighlightElement(calendar.getTimeRangeNotCurrent());
    }
}
