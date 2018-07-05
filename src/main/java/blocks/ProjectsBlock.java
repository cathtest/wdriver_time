package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProjectsBlock {
    private WebDriver driver;
    private By projectNameLocator = By.xpath("//span[@class='item-text' and contains (text(), 'LIBG-CAP')]");
    private By projectNameCell = By.xpath("//span[@class='project-name' and contains (text(), 'LIBG-CAP')]");
    private By nameField = By.cssSelector("input#userNameInput.text.fullWidth");
    private By passwordField = By.cssSelector("input#passwordInput.text.fullWidth");
    private By submitButton = By.cssSelector("span#submitButton.submit");
    private By addActivityButton = By.xpath("//div[@class='task-actions-container']//div[@class='add-activity action']");
    private By activityField = By.cssSelector("input");
    private By activityValueInserted = By.xpath("//input[contains(@title, 'Daily sync-up')]");
    private By todayCell = By.xpath("//div[@class='table-activity-cell cell today']//input[@class='']");
    private By workingDayCell = By.xpath("//div [@class='table-activity-cell cell']//input[@value='']");
    private By workingDayCellFilled = By.xpath("//div [@class='table-activity-cell cell']//input[@value='0.5']");
    private By saveHoursButton = By.xpath("//nav[@class='controller-panel']//button[@class='green button']");
    private By monthsView = By.xpath("//a[@class='switch-item' and contains (text(), 'month')]");
    private By totalHours = By.cssSelector("#total-reported-period");
    private By exportToExcelButton = By.xpath("//span[@class='title' and contains (text(), 'Export to Excel')]");
    private By exportButton = By.xpath("//span[@class = '' and contains (text(), 'Export')]");
    private By currentUser = By.xpath("//span[@class='name']//span[@class='display-value']");
    private By teamMember = By.xpath("//article[@class='member']");
    private By switchTimePeriodButton = By.xpath("//a[@class='switch-item']");
    private By searchButton = By.xpath("//div[@class='magnifying-glass']");
    private By someUserName = By.xpath("//span[@class='full-name' and contains(text(), 'Alexey Alexandrov')]");
    private By searchField = By.xpath("//input[@type='search']");
    private By cancelButton = By.xpath("//div//button[@class='white button' and contains (text(), 'CANCEL')]");
    private By timeRangeNotCurrent = By.xpath("//div[@class='item-day in-current-month']");
    private By calendarButton = By.xpath("//span[@class='date-value']");
    private By workingDayColumnDate = By.xpath("//div[@class='journal-day cell']");

    public ProjectsBlock(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getTimeRangeNotCurrent() {
        return driver.findElement(timeRangeNotCurrent);
    }
    public WebElement getWorkingDayColumnDate() {
        return driver.findElement(workingDayColumnDate);
    }
    public WebElement getCalendarButton() {
        return driver.findElement(calendarButton);
    }
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    public WebElement getSomeUserName() {
        return driver.findElement(someUserName);
    }
    public WebElement getSearchField() {
        return driver.findElement(searchField);
    }
    public WebElement getProjectNameLocator() {
        return driver.findElement(projectNameLocator);
    }
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    public WebElement getSwitchTimePeriodButton() {
        return driver.findElement(switchTimePeriodButton);
    }
    public WebElement getNameField() {
        return driver.findElement(nameField);
    }
    public WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }
    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }
    public WebElement getAddActivityButton() {
        return driver.findElement(addActivityButton);
    }
    public WebElement getActivityField() {
        return driver.findElement(activityField);
    }
    public WebElement getActivityValueInserted() {
        return driver.findElement(activityValueInserted);
    }
    public WebElement getProjectNameCell() {
        return driver.findElement(projectNameCell);
    }
    public WebElement getWorkingDayCell() {
        return driver.findElement(workingDayCell);
    }
    public WebElement getTodayCell() {
        return driver.findElement(todayCell);
    }
    public WebElement getWorkingDayCellFilled() {
        return driver.findElement(workingDayCellFilled);
    }
    public List<WebElement> isWorkingDayCell() {
        return driver.findElements(workingDayCell);
    }
    public WebElement getSaveHoursButton() {
        return driver.findElement(saveHoursButton);
    }
    public WebElement getMonthsView() {
        return driver.findElement(monthsView);
    }
    public WebElement getTotalHours() {
        return driver.findElement(totalHours);
    }
    public WebElement getExportToExcelButton() {
        return driver.findElement(exportToExcelButton);
    }
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    public WebElement getCurrenUser() {
        return driver.findElement(currentUser);
    }
    public List<WebElement> getTeamMember() {
        return driver.findElements(teamMember);
    }
}