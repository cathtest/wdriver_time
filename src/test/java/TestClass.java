import blocks.ProjectsBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
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
    ProjectsBlock nameField;

    @BeforeTest (description = "Start Browser")
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cathy\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        projectsBlock = new ProjectsBlock(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @BeforeTest (dependsOnMethods = {"startBrowser"})
    public void addImplicitly(){
        driver.get(START_URL);
        driver.manage().window().maximize();
    }

    @Test (description = "syncup", priority = 1)
    public void addActivity(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        projectsBlock.getNameField().click();
        projectsBlock.getNameField().sendKeys(USER_NAME);
        projectsBlock.getPasswordField().click();
        projectsBlock.getPasswordField().sendKeys(PASSWORD);
        projectsBlock.getSubmitButton().click();
        projectsBlock.getAddActivityButton().click();
        projectsBlock.getActivityField().sendKeys("Daily sync-up");
        WebElement syncup = projectsBlock.getActivityValueInserted();
        Assert.assertTrue(syncup.isDisplayed(), "The field is not filled in");
    }

    @Test (priority = 2)
    public void projectCoincidence(){
        driver.findElement(By.cssSelector("#choose-project-select")).click();
        WebElement projectDropDown = projectsBlock.getProjectNameLocator();
        WebElement projectCell = projectsBlock.getProjectNameCell();
        String textProjectDropDown = projectDropDown.getText();
        String textProjectCell = projectCell.getText();
        Assert.assertTrue(textProjectDropDown.equals(textProjectCell), "Elements do not coincide");
    }

    @Test(dependsOnMethods = {"addActivity"}, priority = 3)
    public void addHours() {
        projectsBlock.getTodayCell().sendKeys("0.5");
        projectsBlock.getWorkingDayCell().sendKeys("0.5");
        WebElement saveCheck = projectsBlock.getWorkingDayCellFilled();
        while (projectsBlock.isWorkingDayCell().isEmpty() == false) {
            projectsBlock.getWorkingDayCell().sendKeys("0.5");
            Assert.assertTrue(saveCheck.isDisplayed(), "The field is not filled");
        }
    }

    @Test(dependsOnMethods = {"addActivity", "addHours"}, priority = 4)
    public void total(){
        projectsBlock.getSaveHoursButton().click();
        projectsBlock.getMonthsView().click();
        WebElement total = projectsBlock.getTotalHours();
        Assert.assertEquals(total.getText(), "Total 168/168", "The hours do not coincide");
    }

    @Test(dependsOnMethods = {"addActivity", "addHours"}, priority = 5)
    public void totalAll(){
        projectsBlock.getSaveHoursButton().click();
        projectsBlock.getMonthsView().click();
        WebElement total = projectsBlock.getTotalHours();
        total.getText();
        int output = extractInt(total.getText());
        projectsBlock.getCurrenUser().click();
        List<WebElement> list = projectsBlock.getTeamMember();
        int totalAllvalue = 0;
        for(WebElement element : list) {
            element.click();
            int output2 =+ extractInt(projectsBlock.getTotalHours().getText());
            totalAllvalue = output2 + output;
        }
        Assert.assertEquals(totalAllvalue, 15120, "Something went wrong");
    }
    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }

    @Test (dependsOnMethods = {"projectCoincidence","addHours", "addActivity"}, priority = 6)
    public void exportExcel(){
        projectsBlock.getExportToExcelButton().click();
        WebElement export = projectsBlock.getExportButton();
        Assert.assertTrue(export.isDisplayed(), "Export button is not available");

    }

    @AfterClass (description = "Stop Browser")
    public void stopBrowser(){
        driver.quit();
    }


}
