import blocks.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.LogManager;
import utils.Sleeper;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {
    private static final String START_URL = "https://time.epam.com";
    private static final String USER_NAME = "xxxxx";
    private static final String PASSWORD = "xxxxx";
    private WebDriver driver;
    TableJournalBlock tableJournalBlock;
    ControlPanelBlock controlPanelBlock;
    LoginBlock loginBlock;
    ExportExcellBlock exportExcellBlock;
    Calendar calendar;

    @BeforeMethod (description = "Start Browser + login")
    public void startBrowser(){
        LogManager.info("Start browser");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cathy\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        tableJournalBlock = new TableJournalBlock(driver);
        controlPanelBlock = new ControlPanelBlock(driver);
        loginBlock = new LoginBlock(driver);
        calendar = new Calendar(driver);
        exportExcellBlock = new ExportExcellBlock(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        LogManager.info("Browser started");
        LogManager.info("Login");
        driver.get(START_URL);
        driver.manage().window().maximize();
        loginBlock.getNameField().click();
        loginBlock.getNameField().sendKeys(USER_NAME);
        loginBlock.getPasswordField().click();
        loginBlock.getPasswordField().sendKeys(PASSWORD);
        loginBlock.getSubmitButton().click();
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours")
    public void checkThatAddHoursWorkCorrectly(){
        tableJournalBlock.getAddActivityButton().get(0).click();
        tableJournalBlock.getActivityField().sendKeys("Daily sync-up");
        tableJournalBlock.getWorkingDaysList().forEach(cell -> {
            cell.sendKeys("0.5");
        });
        LogManager.info("Checking how the cells are filled with hours");
        Assert.assertTrue(controlPanelBlock.getCellFilled().isDisplayed(), "The field is not filled");
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on projects drop-down list" +
            "\n 3. Get text from the first project"+
            "\n 4. Get text from the first project in the table" +
            "\n 5. Compare the project names")
    public void checkThatProjectsInProjectListAreShownOnTheDashboard() {
//        driver.findElement(By.cssSelector("#choose-project-select")).click();
//        WebElement projectDropDown = projectsBlock.getProjectNameLocator();
//        WebElement projectCell = projectsBlock.getProjectNameCell();
//        String textProjectDropDown = projectDropDown.getText();
//        String textProjectCell = projectCell.getText();
//        LogManager.info("Checking that the project from drop down list coincides with the project from the table");
        controlPanelBlock.getSelectProjectBtn().click();
        for (int i = 1; i < controlPanelBlock.getProjectList().size(); i++) {
            String textProjectDropDown = controlPanelBlock.getProjectList().get(i).getText();
            String textProjectCell = controlPanelBlock.getProjectNameCellList().get(i-1).getText();
            Assert.assertEquals(textProjectDropDown, textProjectCell, "Elements do not coincide");
        }
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. get user total reported time")
    public void total(){
        controlPanelBlock.getMonthsView().click();
        WebElement total = tableJournalBlock.getTotalHours();
        LogManager.info("Checking whether User has filled his time or not");
        Assert.assertEquals(total.getText(), "Total 168/168", "The hours do not coincide");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. Get user total reported time" +
            "\n 4. Add new activity" +
            "\n 5. Add an hour for that activity" +
            "\n 6. Save changes" +
            "\n 7. Verify that total time is changed")
    public void checkThatTotalTimeIsChanged(){
        controlPanelBlock.getMonthsView().click();
        String totalTimeBeforeChanges = tableJournalBlock.getTotalHours().getText();
        tableJournalBlock.getAddActivityButton().get(0).click();
        tableJournalBlock.getActivityField().sendKeys("Some test activity");
        tableJournalBlock.getTodayCell().sendKeys("1");
        controlPanelBlock.getSaveHoursButton().click();
        String totalTimeAfterChanges = tableJournalBlock.getTotalHours().getText();
        LogManager.info("Checking that the total reported time has been changed after logging some new hours");
        Assert.assertFalse(totalTimeBeforeChanges == totalTimeAfterChanges);
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. Click on the user selector" +
            "\n 4. For each user: " +
            "\n 4.1 open user profile" +
            "\n 4.2 get user total reported time")
    public void checkThatAllUsersAreAvailableAndThersTotalHours() {
        controlPanelBlock.getMonthsView().click();
        WebElement total = tableJournalBlock.getTotalHours();
        total.getText();
        controlPanelBlock.getUserSelectorButton().click();
        int QuantityOfTeamMembers = 89;

        for (int i = 0; i < QuantityOfTeamMembers; i++) {// todo dynamic
            controlPanelBlock.getTeamMember().get(i).click();
            int totalHoursForUser = extractInt(tableJournalBlock.getTotalHours().getText());
            controlPanelBlock.getUserSelectorButton().click();
            List<WebElement> list = controlPanelBlock.getTeamMember();

            for (WebElement element: list) {
                LogManager.info("Checking certain user has reported his time");
                Assert.assertEquals(totalHoursForUser, 168, "Something went wrong");
            }
        }
    }

    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Export to Excel" +
            "\n 3. Click on Export button")
    public void exportExcel(){
        String expectedURL = driver.getCurrentUrl();
        exportExcellBlock.getExportToExcelButton().click();
        exportExcellBlock.getExportButton().click();
        String redirectURL = driver.getCurrentUrl();
        Assert.assertEquals(redirectURL, expectedURL);
        LogManager.info("Checking export to Excel");
        Assert.assertEquals(redirectURL, expectedURL, "Export button is not available");
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on 2 weeks button" +
            "\n 3. Verify total time is changed")
    public void checkThatTwoWeeksButtonWorksCorrectly(){
        String totalTimeBeforeChanges = tableJournalBlock.getTotalHours().getText();
        controlPanelBlock.getSwitchTimePeriodButton().click();
        String totalTimeAfterChanges = tableJournalBlock.getTotalHours().getText();
        LogManager.info("Checking time range has been changed");
        Assert.assertFalse(totalTimeBeforeChanges == totalTimeAfterChanges);
    }
    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click the Current User button at the top left corner" +
            "\n 3. Click search button" +
            "\n 4. Enter existing user name" +
            "\n 5. Verify User is found")
    public void checkThatUserSearchIsCorrect(){
        String someName = "Alexey Alexandrov";
        controlPanelBlock.getUserSelectorButton().click();
        controlPanelBlock.getSearchButton().click();
        controlPanelBlock.getSearchField().sendKeys(someName);
        Sleeper.sleep(2);
        String resultSearchName = controlPanelBlock.getSomeUserNameList().get(0).getText();
        System.out.println(resultSearchName);
        LogManager.info("Checking search works correclty");
        Assert.assertEquals(resultSearchName, someName, "Search results do not coincide");
    }
    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours" +
            "\n 7. Refresh the page")
    public void checkThatDataNotSavedAfterRefreshingPage(){
        tableJournalBlock.getAddActivityButton().get(0).click();
        tableJournalBlock.getActivityField().sendKeys("Daily sync-up");
        tableJournalBlock.getWorkingDaysList().forEach(cell -> {
            cell.sendKeys("0.5");
        });
            driver.navigate().refresh();
            LogManager.info("Checking data is not saved after refreshing the page");
        Assert.assertTrue(tableJournalBlock.getWorkingDaysList().isEmpty(),  "There was an error, the field stays filled");
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours" +
            "\n 7. Click Cancel Button")
    public void checkThatDataNotSavedAfterClickingCancel(){
        tableJournalBlock.getAddActivityButton().get(0).click();
        tableJournalBlock.getActivityField().sendKeys("Daily sync-up");
        tableJournalBlock.getWorkingDaysList().forEach(cell -> {
            cell.sendKeys("0.5");
        });
        controlPanelBlock.getCancelButton().click();
        LogManager.info("Checking data is not saved after clicking Cancel button");
        Assert.assertTrue(tableJournalBlock.getWorkingDaysList().isEmpty(), "There was an error, the field stays filled");
    }
    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Click today button"+
            "\n 5. Verify date on the calendar button stays the same as it was at the beginning")
    public void checkThatTodayButtonWorksCorrectly(){
        String dateBeforeSwitch = calendar.getCalendarButton().getText();
        calendar.getCalendarButton().click();
        calendar.getTimeRangeNotCurrent().click();
        controlPanelBlock.getTodayButton().click();
        String dateAfterSwitch = calendar.getCalendarButton().getText();
        LogManager.info("Checking time range is changed");
        Assert.assertTrue(dateAfterSwitch.equals(dateBeforeSwitch), "There was an error, date hasn't been changed");
    }
    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Verify date of the columns in the table has been changed")
    private void checkThatTableIsShownAccordingToCalendarWeek(){
        String columnBeforeSwitch = tableJournalBlock.getWorkingDayColumnDate().getText();
        calendar.getCalendarButton().click();
        calendar.getTimeRangeNotCurrent().click();
        String columnAfterSwitch = tableJournalBlock.getWorkingDayColumnDate().getText();
        LogManager.info("Checking the column has been changed");
        Assert.assertFalse(columnBeforeSwitch == columnAfterSwitch, "There was an error, date hasn't been changed");
    }

    @AfterTest (description = "Stop Browser")
    public void stopBrowser(){
        driver.quit();
    }
}
