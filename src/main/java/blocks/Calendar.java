package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Calendar {
    private WebDriver driver;
    private By calendarButton = By.xpath("//span[@class='date-value']");
    private By timeRangeNotCurrent = By.xpath("//div[@class='item-day in-current-month']");

    public Calendar(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getCalendarButton() {

        return driver.findElement(calendarButton);
    }
    public WebElement getTimeRangeNotCurrent() {

        return driver.findElement(timeRangeNotCurrent);
    }
}
