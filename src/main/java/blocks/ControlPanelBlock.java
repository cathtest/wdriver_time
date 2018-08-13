package blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;
import ru.yandex.qatools.htmlelements.element.*;
import wrapped.ButtonCustom;
import wrapped.DateEntity;
import wrapped.TextBlockCustom;
import wrapped.TextInputCustom;

import java.util.List;

public class ControlPanelBlock extends DefaultPage {

    @FindBy(css = "#choose-project-select > section > div> span")
    private List<TextBlock> projectList;

    @FindBy(id = "choose-project-select")
    private ButtonCustom selectProjectBtn;

    @FindBy(xpath = "//figure[@class='user-photo-container']/../span[@class='name']")
    private ButtonCustom userSelectorButton;

    @FindBy(css = "input.changed")
    private Table cellFilled;

    @FindBy(xpath = "//input[@type='search']")
    private TextInputCustom searchField;

    @FindBy(xpath = "//article[@class='member']")
    private List<TextBlock> teamMember;

    @FindBy(xpath = "//a[@class='switch-item'][1]")
    private TextBlockCustom switchTimePeriodButton;

    @FindBy(id = "goto-today")
    private ButtonCustom todayButton;

    @FindBy(css = "div.period-switch.multi-switch>a.switch-item:nth-of-type(1)")
    private DateEntity weekView;

    @FindBy(css = "p.empty-state")
    private TextBlockCustom noProjectAvailableMessage;

    @FindBy(css = "a.switch-item.active")
    private DateEntity rangeViewSelected;

    @FindBy(xpath = "//div//button[@class='white button' and contains (text(), 'CANCEL')]")
    private ButtonCustom cancelButton;

    @FindBy(xpath = "//nav[@class='controller-panel']//button[@class='green button']")
    private ButtonCustom saveHoursButton;

    @FindBy(css = "div.period-switch.multi-switch>a.switch-item:nth-of-type(3)")
    private ButtonCustom monthsView;

    @FindBy(css = "span.full-name:nth-of-type(1)")
    private TextBlockCustom someUserName;

    @FindBy(xpath = "//div[@class='magnifying-glass']")
    private ButtonCustom searchButton;

    @FindBy (xpath = "//figure[@class='user-photo-container']/../span[@class='export-excel']")
    private ButtonCustom exportToExcelButton;

    @FindBy (css="div.member-info>span.full-name:nth-of-type(1)")
    private List<TextBlock> userNameList;


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

    public ButtonCustom getExportToExcelButton() {
        return exportToExcelButton;
    }

    public  List<TextBlock> getUserNameList() {
        return userNameList;
    }

}