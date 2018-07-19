package unit;

import logics.CalendarLogics;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import services.*;
import utils.LogManager;

import java.util.concurrent.TimeUnit;

public class TestCase {

    private final String START_URL = "https://time.epam.com";
    private final String USER_NAME = "xxxxx@epam.com";
    private final String PASSWORD = "xxxxx";

    protected CalendarLogics calendarLogics;
    protected ExportExcelService exportExcelService;
    protected ControlPanelService controlPanelService;
    protected TableJournalService tableJournalService;
    protected CalendarService calendarService;
    protected WarningMessagesService warningMessagesService;
    protected OvertimeCancellingService overtimeCancellingService;
    protected LoginService loginService;


    @BeforeMethod (description = "Start Browser, Pages and Services initialization, Logging in")
    public void init(){
        startDriver();
        initPages();
        initServices();
        login();
    }

    @AfterTest(description = "Stop Browser")
    public void stopBrowser(){
        DriverManager.getInstance().getDriver().quit();
    }

    //todo ? @BeforeMethod
    public void startDriver(){
        LogManager.info("Start browser");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cathy\\Desktop\\chromedriver.exe"); //todo properties file
        DriverManager.getInstance().createDriver();
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        LogManager.info("Browser started");
        LogManager.info("Login");
        DriverManager.getInstance().getDriver().get(START_URL);
        DriverManager.getInstance().getDriver().manage().window().maximize();
    }

    public void initPages(){
        calendarLogics = new CalendarLogics();
    }

    public void initServices(){
        controlPanelService = new ControlPanelService();
        exportExcelService = new ExportExcelService();
        tableJournalService = new TableJournalService();
        calendarService = new CalendarService();
        warningMessagesService = new WarningMessagesService();
        overtimeCancellingService = new OvertimeCancellingService();
        loginService = new LoginService();
    }

    public void login(){
        LogManager.info("Logging in");
        loginPage.getNameField().click(); //todo service
        loginPage.getNameField().sendKeys(USER_NAME);//todo serv
        loginPage.getPasswordField().click();//todo serv
        loginPage.getPasswordField().sendKeys(PASSWORD);//todo serv
        loginPage.getSubmitButton().click();//todo serv
    }
}
