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
    private By emptyCell = By.xpath("//div[@class='table-activity-cell cell']/input [not(text())][1]");
    private By overtimeMode = By.cssSelector("span.overtime-switcher");
    private By overtimeNotSubmitedCell = By.xpath("//div [@class='table-activity-cell cell edit-overtimes']/span[@class='restrict-edit']");
    private By overtimeEditModeSubmitedCell = By.cssSelector("div.table-activity-cell.cell.overtime-submited.edit-overtimes:nth-child(1)");
    private By overtimeSubmitedCell = By.cssSelector("div.table-activity-cell.cell.overtime-submited:nth-of-type(1)");
    private By overtimeTableInfo = By.cssSelector("section.table-left-part>span.total-reported-period+span");

    public TableJournalBlock(WebDriver driver) {

        this.driver = driver;
    }

    public List<WebElement> getWorkingDaysList() {

        return driver.findElements(workingDays);
    }
    public List<WebElement> getAddActivityButtonList() {

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
    public WebElement getEmptyCell() {

        return driver.findElement(emptyCell);
    }
    public WebElement getOvertimeMode() {

        return driver.findElement(overtimeMode);
    }
    public WebElement getOvertimeSubmitedCell() {

        return driver.findElement(overtimeSubmitedCell);
    }
    public WebElement getOvertimeNotSubmitedCell() {

        return driver.findElement(overtimeNotSubmitedCell);
    }
    public WebElement getOvertimeEditModeSubmitedCell() {

        return driver.findElement(overtimeEditModeSubmitedCell);
    }
    public WebElement getOvertimeTableInfo() {

        return driver.findElement(overtimeTableInfo);
    }
}
