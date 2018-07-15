package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ControlPanelBlock {

    private WebDriver driver;
    private By projectNameCellList = By.xpath("//span[@class='project-name']");
    private By projectList = By.cssSelector("#choose-project-select > section > div> span");
    private By selectProjectBtn = By.id("choose-project-select");
    private By userSelectorButton = By.xpath("//figure[@class='user-photo-container']/../span[@class='name']");
    private By cellFilled = By.xpath("//div[@class='table-week']//input[@class='changed']");
    private By searchField = By.xpath("//input[@type='search']");
    private By searchButton = By.xpath("//div[@class='magnifying-glass']");
    private By someUserName = By.cssSelector("span.full-name:nth-of-type(1)");
    private By monthsView = By.cssSelector("div.period-switch.multi-switch>a.switch-item:nth-of-type(3)");
    private By saveHoursButton = By.xpath("//nav[@class='controller-panel']//button[@class='green button']");
    private By cancelButton = By.xpath("//div//button[@class='white button' and contains (text(), 'CANCEL')]");
    private By teamMember = By.xpath("//article[@class='member']");
    private By switchTimePeriodButton = By.xpath("//a[@class='switch-item'][1]");
    private By todayButton = By.id("goto-today");
    private By weekView = By.cssSelector("div.period-switch.multi-switch>a.switch-item:nth-of-type(1)");

    public ControlPanelBlock(WebDriver driver) {

        this.driver = driver;
    }
    public WebElement getCellFilled(){

        return driver.findElement(cellFilled);
    }
    public WebElement getSelectProjectBtn(){

        return driver.findElement(selectProjectBtn);
    }
    public WebElement getSearchField() {

        return driver.findElement(searchField);
    }
    public WebElement getSomeUserName() {

        return driver.findElement(someUserName);
    }

    public WebElement getSearchButton() {

        return driver.findElement(searchButton);
    }
    public List<WebElement> getProjectNameCellList(){

        return driver.findElements(projectNameCellList);
    }
    public List<WebElement> getProjectList(){

        return driver.findElements(projectList);
    }
    public WebElement getMonthsView() {

        return driver.findElement(monthsView);
    }
    public WebElement getSaveHoursButton() {

        return driver.findElement(saveHoursButton);
    }
    public WebElement getCancelButton() {

        return driver.findElement(cancelButton);
    }
    public List<WebElement> getTeamMember() {

        return driver.findElements(teamMember);
    }
    public WebElement getSwitchTimePeriodButton() {

        return driver.findElement(switchTimePeriodButton);
    }
    public WebElement getUserSelectorButton() {

        return driver.findElement(userSelectorButton);
    }
    public WebElement getTodayButton() {

        return driver.findElement(todayButton);
    }
    public WebElement getWeekView() {

        return driver.findElement(weekView);
    }
}