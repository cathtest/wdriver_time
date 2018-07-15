package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Calendar {
    private WebDriver driver;
    private By calendarButton = By.xpath("//span[@class='date-value']");
    private By timeRangeNotCurrent = By.cssSelector("div.item-day.in-current-month");
    private By anotherView = By.cssSelector("span.label");
    private By theFollowingYear = By.cssSelector("div.year-item.selected+div.year-item");
    private By theFirstMonth = By.cssSelector("div.month-item:nth-of-type(1)");
    private By lastYear = By.xpath("//div[@class='year-item selected']/preceding-sibling::*");
    private By lastMonth = By.xpath("article.calendar-row:nth-of-type(3)>div.month-item:nth-of-type(4)");
    private By theFirstDay = By.cssSelector("article.calendar-row:nth-of-type(6)>div.item-day.in-current-month:nth-of-type(7)");

    public Calendar(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getCalendarButton() {

        return driver.findElement(calendarButton);
    }
    public WebElement getTimeRangeNotCurrent() {

        return driver.findElement(timeRangeNotCurrent);
    }
    public WebElement getAnotherView() {

        return driver.findElement(anotherView);
    }
    public WebElement getTheFollowingYear() {

        return driver.findElement(theFollowingYear);
    }
    public WebElement getTheFirstMonth() {

        return driver.findElement(theFirstMonth);
    }
    public WebElement getLastYear() {

        return driver.findElement(lastYear);
    }
    public WebElement getLastMonth() {

        return driver.findElement(lastMonth);
    }
    public WebElement getTheFirstDay() {

        return driver.findElement(theFirstDay);
    }
}
