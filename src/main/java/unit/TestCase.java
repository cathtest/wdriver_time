package unit;

import blocks.Calendar;
import blocks.ControlPanelBlock;
import blocks.TableJournalBlock;
import blocks.WarningMessagesBlock;
import logics.CalendarLogics;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import pages.ExportExcelPage;
import pages.LoginPage;
import pages.OvertimeCancellingPage;
import utils.LogManager;

import java.util.concurrent.TimeUnit;

public class TestCase {

    private final String START_URL = "https://time.epam.com";
    private final String USER_NAME = "katsiaryna_hrynchuk@epam.com";
    private final String PASSWORD = "Fentfebel4";

    public static WebDriver driver; //todo singleton

    protected TableJournalBlock tableJournalBlock;
    protected ControlPanelBlock controlPanelBlock;
    private LoginPage loginPage;
    protected ExportExcelPage exportExcelPage;
    protected Calendar calendar;
    protected CalendarLogics calendarLogic;
    protected OvertimeCancellingPage overtimeCancelling;
    protected WarningMessagesBlock warningMessages;

    @BeforeMethod(description = "Start Browser + login")
    public void startBrowser() {
        LogManager.info("Start browser");
        System.setProperty("webdriver.chrome.driver", "/Users/katsiaryna_hrynchuk/Downloads/chromedriver");
        driver = new ChromeDriver();
        tableJournalBlock = new TableJournalBlock(driver);
        controlPanelBlock = new ControlPanelBlock(driver);
        loginPage = new LoginPage(driver);
        calendar = new Calendar(driver);
        calendarLogic = new CalendarLogics(calendar);
        exportExcelPage = new ExportExcelPage(driver);
        warningMessages = new WarningMessagesBlock(driver);
        overtimeCancelling = new OvertimeCancellingPage(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        LogManager.info("Browser started");
        LogManager.info("Login");
        driver.get(START_URL);
        driver.manage().window().maximize();
        loginPage.getNameField().click();
        loginPage.getNameField().sendKeys(USER_NAME);
        loginPage.getPasswordField().click();
        loginPage.getPasswordField().sendKeys(PASSWORD);
        loginPage.getSubmitButton().click();
    }

    //annotations
    public void startDriver(){}

    //annotations
    public void login(){}

    //annotations
    public void initPages(){}
}
