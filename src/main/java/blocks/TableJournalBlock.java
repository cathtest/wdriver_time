package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

import java.util.List;

public class TableJournalBlock extends DefaultPage {

    private WebDriver driver;

    @FindAll(@FindBy(xpath = "//div[@class='table-week']/div[not(contains(@class, 'not-workday'))]/input"))
    private List<WebElement> workingDays;

    @FindAll(@FindBy(xpath = "//div[@class='add-activity action']/i"))
    private List<WebElement> addActivityButton;

    @FindBy(xpath = "//div[@class='table-activity-name']/input")
    private WebElement activityField;

    @FindBy(xpath = "//div[@class='table-activity-cell cell today']/input")
    private WebElement todayCell;

    @FindBy(css = "#total-reported-period")
    private WebElement totalHours;

    @FindBy(css = "div.journal-day.cell:nth-of-type(1)")
    private WebElement workingDayColumnDate;

    @FindBy(xpath = "//div[@class='table-activity-cell cell']/input[not(text())][1]")
    private WebElement emptyCell;

    @FindBy(css = "span.overtime-switcher")
    private WebElement overtimeMode;

    @FindBy(xpath = "//div [@class='table-activity-cell cell edit-overtimes']/span[@class='restrict-edit']")
    private WebElement overtimeNotSubmitedCell;

    @FindBy(css = "div.table-activity-cell.cell.overtime-submited.edit-overtimes:nth-child(1)")
    private WebElement overtimeEditModeSubmitedCell;

    @FindBy(css = "div.table-activity-cell.cell.overtime-submited:nth-of-type(1)")
    private WebElement overtimeSubmitedCell;

    @FindBy(css = "section.table-left-part>span.total-reported-period+span")
    private WebElement overtimeTableInfo;


    public TableJournalBlock(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getWorkingDaysList() {
        return  workingDays;
    }

    public List<WebElement> getAddActivityButtonList() {
        return (List<WebElement>) addActivityButton;
    }

    public WebElement getTodayCell() {
        return todayCell;
    }

    public WebElement getActivityField() {
        return activityField;
    }

    public WebElement getTotalHours() {
        return totalHours;
    }

    public WebElement getWorkingDayColumnDate() {
        return workingDayColumnDate;
    }

    public WebElement getEmptyCell() {
        return emptyCell;
    }

    public WebElement getOvertimeMode() {
        return overtimeMode;
    }

    public WebElement getOvertimeSubmitedCell() {
        return overtimeSubmitedCell;
    }

    public WebElement getOvertimeNotSubmitedCell() {
        return overtimeNotSubmitedCell;
    }

    public WebElement getOvertimeEditModeSubmitedCell() {
        return overtimeEditModeSubmitedCell;
    }

    public WebElement getOvertimeTableInfo() {
        return overtimeTableInfo;
    }
}
