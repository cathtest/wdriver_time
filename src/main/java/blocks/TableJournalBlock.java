package blocks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Table;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.awt.*;
import java.util.List;

public class TableJournalBlock extends DefaultPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[@class='project-name']")
    private List<WebElement> projectNameCellList;

    @FindBy(xpath = "//div[@class='table-week']/div[not(contains(@class, 'not-workday'))]/input")
    private List<WebElement> workingDays;

    @FindBy(xpath = "//div[@class='add-activity action']/i")
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
    private Table emptyCell;

    @FindBy(css = "span.overtime-switcher")
    private Button overtimeMode;

    @FindBy(xpath = "//div [@class='table-activity-cell cell edit-overtimes']/span[@class='restrict-edit']")
    private Table overtimeNotSubmitedCell;

    @FindBy(css = "div.table-activity-cell.cell.overtime-submited.edit-overtimes:nth-child(1)")
    private Table overtimeEditModeSubmitedCell;

    @FindBy(css = "div.table-activity-cell.cell.overtime-submited:nth-of-type(1)")
    private Table overtimeSubmitedCell;

    @FindBy(css = "section.table-left-part>span.total-reported-period+span")
    private TextBlock overtimeTableInfo;


    public List<WebElement> getProjectNameCellList(){
        return projectNameCellList;
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
