package com.kate.mentoring.java.pages;

import com.kate.mentoring.java.wrapped.ButtonCustom;
import com.kate.mentoring.java.wrapped.TableCustom;
import com.kate.mentoring.java.wrapped.TextBlockCustom;
import com.kate.mentoring.java.wrapped.TextInputCustom;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Table;

import java.util.List;

public class TableJournal extends DefaultPage {

    @FindBy(xpath = "//span[@class='project-name']")
    private List<Table> projectNameCellList;

    @FindBy(xpath = "//div[@class='table-week']/div[not(contains(@class, 'not-workday'))]/input")
    private List<Table> workingDays;

    @FindBy(xpath = "//div[@class='add-activity action']/i")
    private List<Button> addActivityButton;

    @FindBy(xpath = "//div[@class='table-activity-name']/input")
    private TextInputCustom activityField;

    @FindBy(xpath = "//div[@class='table-activity-cell cell today']/input")
    private TableCustom todayCell;

    @FindBy(css = "#total-reported-period")
    private TextInputCustom totalHours;

    @FindBy(css = "div.journal-day.cell:nth-of-type(1)")
    private TableCustom workingDayColumnDate;

    @FindBy(xpath = "//div[@class='table-activity-cell cell']/input[not(text())][1]")
    private TableCustom emptyCell;

    @FindBy(css = "span.overtime-switcher")
    private ButtonCustom overtimeMode;

    @FindBy(xpath = "//div [@class='table-activity-cell cell edit-overtimes']/span[@class='restrict-edit']")
    private TableCustom overtimeNotSubmitedCell;

    @FindBy(css = "div.table-activity-cell.cell.overtime-submited.edit-overtimes:nth-child(1)")
    private TableCustom overtimeEditModeSubmitedCell;

    @FindBy(css = "div.table-activity-cell.cell.overtime-submited:nth-of-type(1)")
    private TableCustom overtimeSubmitedCell;

    @FindBy(css = "section.table-left-part>span.total-reported-period+span")
    private TextBlockCustom overtimeTableInfo;


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
