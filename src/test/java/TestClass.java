import blocks.ProjectsBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
    private static final String USER_NAME = "katsiaryna_hrynchuk@epam.com";
    private static final String PASSWORD = "Fentfebel4";
    private WebDriver driver;
    ProjectsBlock projectsBlock;

    @BeforeMethod (description = "Start Browser")
    public void startBrowser(){
        LogManager.info("Start browser");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cathy\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        projectsBlock = new ProjectsBlock(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        LogManager.info("Browser started");
        LogManager.info("Login");
        driver.get(START_URL);
        driver.manage().window().maximize();
        projectsBlock.getNameField().click();
        projectsBlock.getNameField().sendKeys(USER_NAME);
        projectsBlock.getPasswordField().click();
        projectsBlock.getPasswordField().sendKeys(PASSWORD);
        projectsBlock.getSubmitButton().click();
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours")
    public void checkThatAddHoursWorkCorrectly(){
        projectsBlock.getAddActivityButton().click();
        projectsBlock.getActivityField().sendKeys("Daily sync-up");
        projectsBlock.getTodayCell().sendKeys("0.5");
        projectsBlock.getWorkingDayCell().sendKeys("0.5");
        WebElement saveCheck = projectsBlock.getWorkingDayCellFilled();
        while (projectsBlock.isWorkingDayCell().isEmpty() == false) {
            projectsBlock.getWorkingDayCell().sendKeys("0.5");
        }
        LogManager.info("Checking how the cells are filled with hours");
        Assert.assertTrue(saveCheck.isDisplayed(), "The field is not filled");
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on projects drop-down list" +
            "\n 3. Get text from the first project"+
            "\n 4. Get text from the first project in the table" +
            "\n 5. Compare the project names")
    public void checkThatProjectsInProjectListAreShownOnTheDashboard(){
        driver.findElement(By.cssSelector("#choose-project-select")).click();
        WebElement projectDropDown = projectsBlock.getProjectNameLocator();
        WebElement projectCell = projectsBlock.getProjectNameCell();
        String textProjectDropDown = projectDropDown.getText();
        String textProjectCell = projectCell.getText();
        LogManager.info("Checking that the project from drop down list coincides with the project from the table");
        Assert.assertTrue(textProjectDropDown.equals(textProjectCell), "Elements do not coincide");
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Go to month view" +
            "\n 3. get user total reported time")
    public void total(){
        projectsBlock.getMonthsView().click();
        WebElement total = projectsBlock.getTotalHours();
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
        projectsBlock.getMonthsView().click();
        String totalTimeBeforeChanges = projectsBlock.getTotalHours().getText();
        projectsBlock.getActivityField().sendKeys("Some test activity");
        projectsBlock.getTodayCell().sendKeys("1");
        projectsBlock.getSaveHoursButton().click();
        String totalTimeAfterChanges = projectsBlock.getTotalHours().getText();
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
        projectsBlock.getMonthsView().click();
        WebElement total = projectsBlock.getTotalHours();
        total.getText();
        projectsBlock.getCurrenUser().click();
        int QuantityOfTeamMembers = 89;

        for (int i = 0; i < QuantityOfTeamMembers; i++) {// todo dynamic
            projectsBlock.getTeamMember().get(i).click();
            int totalHoursForUser = extractInt(projectsBlock.getTotalHours().getText());
            projectsBlock.getCurrenUser().click();
            List<WebElement> list = projectsBlock.getTeamMember();

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
        projectsBlock.getExportToExcelButton().click();
        WebElement export = projectsBlock.getExportButton();
        LogManager.info("Checking export to Excel");
        Assert.assertTrue(export.isDisplayed(), "Export button is not available");
    }

    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on 2 weeks button" +
            "\n 3. Verify total time is changed")
    public void checkThatTwoWeeksButtonWorksCorrectly(){
        String totalTimeBeforeChanges = projectsBlock.getTotalHours().getText();
        projectsBlock.getSwitchTimePeriodButton().click();
        String totalTimeAfterChanges = projectsBlock.getTotalHours().getText();
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
        projectsBlock.getCurrenUser().click();
        projectsBlock.getSearchButton().click();
        Sleeper.sleep(2);
        projectsBlock.getSearchField().sendKeys(someName);
        String resultSearchName = projectsBlock.getSomeUserName().getText();
        System.out.println(resultSearchName);
        LogManager.info("Checking search works correclty");
        Assert.assertTrue(someName.equals(resultSearchName));
    }
    @Test (expectedExceptions = StaleElementReferenceException.class,
            description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours" +
            "\n 7. Refresh the page")
    public void checkThatDataNotSavedAfterRefreshingPage(){
            projectsBlock.getAddActivityButton().click();
            projectsBlock.getActivityField().sendKeys("Test activity");
            projectsBlock.getTodayCell().sendKeys("0.5");
            projectsBlock.getWorkingDayCell().sendKeys("0.5");
            WebElement saveCheck = projectsBlock.getWorkingDayCellFilled();
            while (projectsBlock.isWorkingDayCell().isEmpty() == false){
                projectsBlock.getWorkingDayCell().sendKeys("0.5");
            }
            driver.navigate().refresh();
            LogManager.info("Checking data is not saved after refreshing the page");
            Assert.assertFalse(saveCheck.isDisplayed(),  "There was an error, the field stays filled");
    }

    @Test (expectedExceptions = StaleElementReferenceException.class,
            description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Add activity button" +
            "\n 3. Enter activity name in the field" +
            "\n 4. Click on today cell in the table" +
            "\n 5. Enter the quantity of hours in the field" +
            "\n 6. For each working day with the exception of today enter the quantity of hours" +
            "\n 7. Click Cancel Button")
    public void checkThatDataNotSavedAfterClickingCancel(){
        projectsBlock.getAddActivityButton().click();
        projectsBlock.getActivityField().sendKeys("Daily sync-up");
        projectsBlock.getTodayCell().sendKeys("0.5");
        projectsBlock.getWorkingDayCell().sendKeys("0.5");
        WebElement saveCheck = projectsBlock.getWorkingDayCellFilled();
        while (projectsBlock.isWorkingDayCell().isEmpty() == false) {
            projectsBlock.getWorkingDayCell().sendKeys("0.5");
        }
        projectsBlock.getCancelButton().click();
        LogManager.info("Checking data is not saved after clicking Cancel button");
        Assert.assertTrue(saveCheck.isDisplayed(), "There was an error, the field stays filled");
    }
    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Verify date on the calendar has been changed")
    public void checkThatTodayButtonWorksCorrectly(){
        String dateBeforeSwitch = projectsBlock.getCalendarButton().getText();
        projectsBlock.getCalendarButton().click();
        projectsBlock.getTimeRangeNotCurrent().click();
        String dateAfterSwitch = projectsBlock.getCalendarButton().getText();
        Assert.assertFalse(dateAfterSwitch.equals(dateBeforeSwitch), "There was an error, date hasn't been changed");
    }
    @Test (description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on Calendar button" +
            "\n 3. Choose the time range that is not equal to the current time range" +
            "\n 4. Verify date of the columns in the table has been changed")
    private void checkThatTableIsShownAccordingToCalendarWeek(){
        String columnBeforeSwitch = projectsBlock.getWorkingDayColumnDate().getText();
        projectsBlock.getCalendarButton().click();
        projectsBlock.getTimeRangeNotCurrent().click();
        String columnAfterSwitch = projectsBlock.getWorkingDayColumnDate().getText();
        Assert.assertFalse(columnBeforeSwitch.equals(columnAfterSwitch), "There was an error, date hasn't been changed");
    }

    @AfterTest (description = "Stop Browser")
    public void stopBrowser(){
        driver.quit();
    }
}
