package blocks;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;
import ru.yandex.qatools.htmlelements.element.*;
import unit.DriverManager;
import utils.Sleeper;

import java.util.List;

public class ControlPanelBlock extends DefaultPage {

    @FindBy(css = "#choose-project-select > section > div> span")
    private List<TextBlock> projectList;

    @FindBy(id = "choose-project-select")
    private Button selectProjectBtn;

    @FindBy(xpath = "//figure[@class='user-photo-container']/../span[@class='name']")
    private Button userSelectorButton;

    @FindBy(css = "input.changed")
    private Table cellFilled;

    @FindBy(xpath = "//input[@type='search']")
    private TextInput searchField;

    @FindBy(xpath = "//article[@class='member']")
    private List<TextBlock> teamMember;

    @FindBy(xpath = "//a[@class='switch-item'][1]")
    private TextBlock switchTimePeriodButton;

    @FindBy(id = "goto-today")
    private Button todayButton;

    @FindBy(css = "div.period-switch.multi-switch>a.switch-item:nth-of-type(1)")
    private TextBlock weekView;

    @FindBy(css = "p.empty-state")
    private TextBlock noProjectAvailableMessage;

    @FindBy(css = "a.switch-item.active")
    private TextBlock rangeViewSelected;

    @FindBy(xpath = "//div//button[@class='white button' and contains (text(), 'CANCEL')]")
    private Button cancelButton;

    @FindBy(xpath = "//nav[@class='controller-panel']//button[@class='green button']")
    private Button saveHoursButton;

    @FindBy(css = "div.period-switch.multi-switch>a.switch-item:nth-of-type(3)")
    private Button monthsView;

    @FindBy(css = "span.full-name:nth-of-type(1)")
    private TextBlock someUserName;

    @FindBy(xpath = "//div[@class='magnifying-glass']")
    private Button searchButton;

    @FindBy (xpath = "//figure[@class='user-photo-container']/../span[@class='export-excel']")
    private Button exportToExcelButton;

    @FindBy (css="div.member-info>span.full-name:nth-of-type(1)")
    private List<WebElement> userNameList;


    public WebElement getCellFilled(){
        return cellFilled;
    }

    public WebElement getSelectProjectBtn(){
        return selectProjectBtn;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getSomeUserName() {
        return someUserName;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public List<TextBlock> getProjectList(){
        return projectList;
    }

    public WebElement getMonthsView() {
        return monthsView;
    }

    public WebElement getSaveHoursButton() {
        return saveHoursButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public List<TextBlock> getTeamMemberList() {
        return teamMember;
    }

    public WebElement getSwitchTimePeriodButton() {
        return switchTimePeriodButton;
    }

    public WebElement getUserSelectorButton() {
        return userSelectorButton;
    }

    public WebElement getTodayButton() {
        return todayButton;
    }

    public WebElement getWeekView() {
        return weekView;
    }

    public WebElement getNoProjectAvailableMessage() {
        return noProjectAvailableMessage;
    }

    public WebElement getRangeViewSelected() {
        return rangeViewSelected;
    }

    public WebElement getExportToExcelButton() {
        return exportToExcelButton;
    }

    public  List<WebElement> getUserNameList() {
        return userNameList;
    }

}