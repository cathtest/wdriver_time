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
    private static final String USER_NAME = "xxxxx";
    private static final String PASSWORD = "xxxxx";
    private WebDriver driver;

    @BeforeTest (description = "Start Browser")
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cathy\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeTest (dependsOnMethods = {"startBrowser"})
    public void addImplicitly(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(START_URL);
        driver.manage().window().maximize();
    }

    @Test (description = "syncup", priority = 1)
    public void addActivity(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("input#userNameInput.text.fullWidth")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("input#userNameInput.text.fullWidth")).sendKeys(USER_NAME);
        driver.findElement(By.cssSelector("input#passwordInput.text.fullWidth")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("input#passwordInput.text.fullWidth")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("span#submitButton.submit")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='task-actions-container']//div[@class='add-activity action']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("input")).sendKeys("Daily sync-up");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement syncup = driver.findElement(By.xpath("//input[contains(@title, 'Daily sync-up')]"));
        Assert.assertTrue(syncup.isDisplayed(), "The field is not filled in");
    }

    @Test (priority = 2)
    public void projectCoincidence(){
        driver.findElement(By.cssSelector("#choose-project-select")).click();
        WebElement project0 = driver.findElement(By.xpath("//span[@class='item-text' and contains (text(), 'LIBG-CAP')]"));
        String text0 = project0.getText();
        WebElement project1 = driver.findElement(By.xpath("//span[@class='project-name' and contains (text(), 'LIBG-CAP')]"));
        String text1 = project1.getText();
        Assert.assertTrue(text0.equals(text1), "Elements do not coincide");
    }

    @Test(dependsOnMethods = {"addActivity"}, priority = 3)
    public void addHours() {
        driver.findElement(By.xpath("//div [@class='table-activity-cell cell']//input")).click();
        driver.findElement(By.xpath("//div [@class='table-activity-cell cell']//input")).sendKeys("0.5");
        WebElement saveCheck = driver.findElement(By.xpath("//div [@class='table-activity-cell cell']//input[@value='0.5']"));
        while (driver.findElements(By.xpath("//div [@class='table-activity-cell cell']//input[@value='']")).isEmpty() == false) {
            driver.findElement(By.xpath("//div [@class='table-activity-cell cell']//input[@value='']")).click();
            driver.findElement(By.xpath("//div [@class='table-activity-cell cell']//input[@value='']")).sendKeys("0.5");
            Assert.assertTrue(saveCheck.isDisplayed(), "The field is not filled");
        }
    }

    @Test(dependsOnMethods = {"addActivity", "addHours"}, priority = 4)
    public void total(){
        driver.findElement(By.xpath("//nav[@class='bottom-controller show']//button[@class='green button']")).click();
        driver.findElement(By.xpath("//a[@class='switch-item' and contains (text(), 'month')]")).click();
        WebElement el0 = driver.findElement(By.cssSelector("#total-reported-period"));
        Assert.assertEquals(el0.getText(), "Total 168/168", "The hours do not coincide");
    }

    @Test(dependsOnMethods = {"addActivity", "addHours"}, priority = 5)
    public void totalAll(){
        driver.findElement(By.xpath("//nav[@class='bottom-controller show']//button[@class='green button']")).click();
        driver.findElement(By.xpath("//a[@class='switch-item' and contains (text(), 'month')]")).click();
        WebElement el0 = driver.findElement(By.cssSelector("#total-reported-period"));
        el0.getText();
        int output = extractInt(el0.getText());
        driver.findElement(By.xpath("//span[@class='name']//span[@class='display-value']")).click();
        List<WebElement> list = driver.findElements(By.xpath("//article[@class='member']"));
        int totalAllvalue = 0;
        for(WebElement element : list) {
            element.click();
            int output2 =+ extractInt(driver.findElement(By.cssSelector("#total-reported-period")).getText());
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
        driver.findElement(By.xpath("//span[@class='title' and contains (text(), 'Export to Excel')]")).click();
        WebElement export = driver.findElement(By.xpath("//span[@class = '' and contains (text(), 'Export')]"));
        System.out.println(export.getText());
        Assert.assertTrue(export.isDisplayed(), "Export button is not available");

    }

    @AfterClass (description = "Stop Browser")
    public void stopBrowser(){
        driver.quit();
    }


}
