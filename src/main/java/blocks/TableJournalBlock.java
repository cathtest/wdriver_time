package blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Table;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;
import java.util.List;

public class TableJournalBlock extends DefaultPage {

    @FindBy(xpath = "//span[@class='project-name']")
    private List<Table> projectNameCellList;

    @FindBy(xpath = "//div[@class='table-week']/div[not(contains(@class, 'not-workday'))]/input")
    private List<Table> workingDays;

    @FindBy(xpath = "//div[@class='add-activity action']/i")
    private List<Button> addActivityButton;

    @FindBy(xpath = "//div[@class='table-activity-name']/input")
    private TextInput activityField;

    @FindBy(xpath = "//div[@class='table-activity-cell cell today']/input")
    private Table todayCell;

    @FindBy(css = "#total-reported-period")
    private TextInput totalHours;

    @FindBy(css = "div.journal-day.cell:nth-of-type(1)")
    private Table workingDayColumnDate;

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


    public List<Table> getProjectNameCellList(){
        return projectNameCellList;
    }

    public List<Table> getWorkingDaysList() {
        return  workingDays;
    }

    public List<Button> getAddActivityButtonList() {
        return addActivityButton;
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
