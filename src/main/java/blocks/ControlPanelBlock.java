package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

import java.util.List;

public class ControlPanelBlock extends DefaultPage {

    @FindBy(css = "#choose-project-select > section > div> span")
    private List<WebElement> projectList;

    @FindBy(id = "choose-project-select")
    private WebElement selectProjectBtn;

    @FindBy(xpath = "//figure[@class='user-photo-container']/../span[@class='name']")
    private WebElement userSelectorButton;

    @FindBy(xpath = "//div[@class='table-week']//input[@class='changed']")
    private WebElement cellFilled;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchField;

    @FindBy(xpath = "//article[@class='member']")
    private List<WebElement> teamMember;

    @FindBy(xpath = "//a[@class='switch-item'][1]")
    private WebElement switchTimePeriodButton;

    @FindBy(id = "goto-today")
    private WebElement todayButton;

    @FindBy(css = "div.period-switch.multi-switch>a.switch-item:nth-of-type(1)")
    private WebElement weekView;

    @FindBy(css = "p.empty-state")
    private WebElement noProjectAvailableMessage;

    @FindBy(css = "a.switch-item.active")
    private WebElement rangeViewSelected;

    @FindBy(xpath = "//div//button[@class='white button' and contains (text(), 'CANCEL')]")
    private WebElement cancelButton;

    @FindBy(xpath = "//nav[@class='controller-panel']//button[@class='green button']")
    private WebElement saveHoursButton;

    @FindBy(css = "div.period-switch.multi-switch>a.switch-item:nth-of-type(3)")
    private WebElement monthsView;

    @FindBy(css = "span.full-name:nth-of-type(1)")
    private WebElement someUserName;

    @FindBy(xpath = "//div[@class='magnifying-glass']")
    private WebElement searchButton;

    @FindBy (xpath = "//figure[@class='user-photo-container']/../span[@class='export-excel']")
    private WebElement exportToExcelButton;


    public ControlPanelBlock(WebDriver driver) {
        super(driver);
    }

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

    public List<WebElement> getProjectList(){
        return (List<WebElement>)projectList;
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

    public List<WebElement> getTeamMemberList() {
        return (List<WebElement>) teamMember;
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
}