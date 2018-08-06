package services;
import blocks.Calendar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import unit.DriverManager;


import java.util.List;

public class CalendarService extends ClickService{

    private Calendar calendar = new Calendar();

    ClickService clickService = new ClickService(DriverManager.getInstance().getDriver());

    public CalendarService(WebDriver driver) {
        super(driver);
    }

//    public CalendarService() {
//        this.calendar = new Calendar();
//    }

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
}
