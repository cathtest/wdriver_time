package unit;
import logics.CalendarLogics;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import services.*;
import utils.LogManager;

import java.util.concurrent.TimeUnit;

public class TestCase {

    private final String START_URL = "https://time.epam.com";
    private final String USER_NAME = "katsiaryna_hrynchuk@epam.com";
    private final String PASSWORD = "Fentfebel4";

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
        DriverManager.getInstance().getDriver().get(START_URL);
        DriverManager.getInstance().getDriver().manage().window().maximize();
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
        loginService.nameFieldClick();//todo service
        loginService.nameFieldSendKeys(USER_NAME);//todo serv
        loginService.passwordFieldClick();//todo serv
        loginService.passwordFieldSendKeys(PASSWORD);//todo serv
        loginService.submitButtonClick();//todo serv
    }
}
