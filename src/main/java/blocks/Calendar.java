package blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

import java.util.List;

public class Calendar extends DefaultPage {

    @FindBy(xpath = "//span[@class='date-value']")
    private WebElement calendarButton;

    @FindBy(css = "div.item-day.in-current-month")
    private WebElement timeRangeNotCurrent;

    @FindBy(css = "span.label")
    private WebElement anotherView;

    @FindBy(css = "div.year-item.selected+div.year-item")
    private WebElement theFollowingYear;

    @FindBy(css = "div.month-item:nth-of-type(1)")
    private WebElement theFirstMonth;

    @FindBy(xpath = "//div[@class='year-item selected']/preceding-sibling::*")
    private WebElement lastYear;

    @FindBy(css = "article.calendar-row:nth-of-type(3)>div.month-item:nth-of-type(4)")
    private WebElement lastMonth;

    @FindBy(css = "article.calendar-row:nth-of-type(6)>div.item-day.in-current-month:nth-of-type(7)")
    private WebElement theFirstDay;

    @FindBy(xpath = "//article[@class='calendar-row'][1]/div[@class='year-item'][1]")
    private WebElement leftCornerYear;

    @FindBy(css = "span.prev")
    private WebElement leftArrow;

    @FindBy(css = "span.next")
    private WebElement rightArrow;

    @FindBy(css = "div.year-item")
    private List <WebElement> yearsList;

    public WebElement getCalendarButton() {
        return calendarButton;
    }

    public WebElement getTimeRangeNotCurrent() {
        return timeRangeNotCurrent;
    }

    public WebElement getAnotherView() {
        return anotherView;
    }

    public WebElement getTheFollowingYear() {
        return theFollowingYear;
    }

    public WebElement getTheFirstMonth() {
        return theFirstMonth;
    }

    public WebElement getLastYear() {
        return lastYear;
    }

    public WebElement getLastMonth() {
        return lastMonth;
    }

    public WebElement getTheFirstDay() {
        return theFirstDay;
    }

    public WebElement getLeftCornerYear() {
        return leftCornerYear;
    }

    public WebElement getLeftArrow() {
        return leftArrow;
    }

    public WebElement getRightArrow() {
        return rightArrow;
    }

    public List<WebElement> getYearsList() {
        return (List<WebElement>)yearsList;
    }
}
