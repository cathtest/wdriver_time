package unit;
import blocks.Calendar;
import blocks.ControlPanelBlock;
import blocks.TableJournalBlock;
import blocks.WarningMessagesBlock;
import logics.CalendarLogics;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import pages.ExportExcelPage;
import pages.LoginPage;
import pages.OvertimeCancellingPage;
import services.*;
import utils.LogManager;
import java.util.concurrent.TimeUnit;

public class TestCase {

    private final String START_URL = "https://time.epam.com";
    private final String USER_NAME = "xxxxx@epam.com";
    private final String PASSWORD = "xxxxx";

    public static WebDriver driver;
    protected TableJournalBlock tableJournalBlock;
    protected ControlPanelBlock controlPanelBlock;
    private LoginPage loginPage;
    protected ExportExcelPage exportExcelPage;
    protected Calendar calendar;
    protected CalendarLogics calendarLogics;
    protected OvertimeCancellingPage overtimeCancellingPage;
    protected WarningMessagesBlock warningMessagesBlock;
    protected ExportExcelService exportExcelService;
    protected ControlPanelService controlPanelService;
    protected TableJournalService tableJournalService;
    protected CalendarService calendarService;
    protected WarningMessagesService warningMessagesService;
    protected OvertimeCancellingService overtimeCancellingService;
    protected Singleton singleton;

    @BeforeMethod (description = "Start Browser, Pages and Services initialization, Logging in")
    public void init(){
        startDriver();
        initPages();
        initServices();
        login();
    }

    @AfterTest(description = "Stop Browser")
    public void stopBrowser(){
        driver.quit();
    }

    public void startDriver(){
        LogManager.info("Start browser");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cathy\\Desktop\\chromedriver.exe");
        driver = singleton.getInstance();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        LogManager.info("Browser started");
        LogManager.info("Login");
        driver.get(START_URL);
        driver.manage().window().maximize();
    }

    public void initPages(){
        tableJournalBlock = new TableJournalBlock(driver);
        controlPanelBlock = new ControlPanelBlock(driver);
        loginPage = new LoginPage(driver);
        calendar = new Calendar(driver);
        calendarLogics = new CalendarLogics(calendar);
        exportExcelPage = new ExportExcelPage(driver);
        warningMessagesBlock = new WarningMessagesBlock(driver);
        overtimeCancellingPage = new OvertimeCancellingPage(driver);
    }

    public void initServices(){
        controlPanelService = new ControlPanelService();
        exportExcelService = new ExportExcelService();
        tableJournalService = new TableJournalService();
        calendarService = new CalendarService();
        warningMessagesService = new WarningMessagesService();
        overtimeCancellingService = new OvertimeCancellingService();
    }

    public void login(){
        LogManager.info("Logging in");
        loginPage.getNameField().click();
        loginPage.getNameField().sendKeys(USER_NAME);
        loginPage.getPasswordField().click();
        loginPage.getPasswordField().sendKeys(PASSWORD);
        loginPage.getSubmitButton().click();
    }
}
