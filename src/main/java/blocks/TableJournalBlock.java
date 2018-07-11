package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TableJournalBlock {

    private WebDriver driver;
    private By workingDays = By.xpath("//div[@class='table-week']/div[not(contains(@class, 'not-workday'))]/input");
    private By addActivityButton = By.xpath("//div[@class='add-activity action']/i");
    private By activityField = By.xpath("//div[@class='table-activity-name']/input");
    private By todayCell = By.xpath("//div[@class='table-activity-cell cell today']/input");
    private By totalHours = By.cssSelector("#total-reported-period");
    private By workingDayColumnDate = By.cssSelector("div.journal-day.cell:nth-of-type(1)");
    public TableJournalBlock(WebDriver driver) {

        this.driver = driver;
    }

    public List<WebElement> getWorkingDaysList() {

        return driver.findElements(workingDays);
    }
    public List<WebElement> getAddActivityButton() {

        return driver.findElements(addActivityButton);
    }
    public WebElement getTodayCell() {

        return driver.findElement(todayCell);
    }
    public WebElement getActivityField() {

        return driver.findElement(activityField);
    }
    public WebElement getTotalHours() {

        return driver.findElement(totalHours);
    }
    public WebElement getWorkingDayColumnDate() {

        return driver.findElement(workingDayColumnDate);
    }
}
