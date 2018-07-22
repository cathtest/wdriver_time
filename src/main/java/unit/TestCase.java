package unit;
import logics.CalendarLogics;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import services.*;
import utils.LogManager;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestCase {

    private String START_URL;
    private  String USER_NAME;
    private  String PASSWORD;
    private String driverType;
    private String driverPath;

    protected CalendarLogics calendarLogics;
    protected ExportExcelService exportExcelService;
    protected ControlPanelService controlPanelService;
    protected TableJournalService tableJournalService;
    protected CalendarService calendarService;
    protected WarningMessagesService warningMessagesService;
    protected OvertimeCancellingService overtimeCancellingService;
    protected LoginService loginService;
    protected LogManager logManager ;


    @BeforeMethod (description = "Initializing variables from properties", groups = "first")
    public void initProperties(){
        try(FileReader reader = new FileReader("keys.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            START_URL = properties.getProperty("START_URL");
            USER_NAME = properties.getProperty("USER_NAME");
            PASSWORD = properties.getProperty("PASSWORD");
            driverType = properties.getProperty("driverType");
            driverPath = properties.getProperty("driverPath");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @BeforeMethod(description = "Starting Logger", groups = "second", dependsOnGroups = "first")
    public void startLogger(){
        logManager = new LogManager();
    }

    @BeforeMethod (description = "Starting driver", groups = "third", dependsOnGroups = "second")
    public void startDriver(){
        logManager.loggingInfo("Start browser");
        System.setProperty(driverType, driverPath);
        DriverManager.getInstance().createDriver();
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        logManager.loggingInfo("Browser started");
        DriverManager.getInstance().getDriver().get(START_URL);
        DriverManager.getInstance().getDriver().manage().window().maximize();
    }

    @BeforeMethod (description = "Initializing services", groups = "forth", dependsOnGroups = "third")
    public void initServices(){
        controlPanelService = new ControlPanelService();
        exportExcelService = new ExportExcelService();
        tableJournalService = new TableJournalService();
        calendarService = new CalendarService();
        warningMessagesService = new WarningMessagesService();
        overtimeCancellingService = new OvertimeCancellingService();
        loginService = new LoginService();
        calendarLogics = new CalendarLogics();
    }

    @BeforeMethod(description = "Logging in", groups = "fifth", dependsOnGroups = "forth")
    public void login(){
        logManager.loggingInfo("Logging in");
        loginService.nameFieldClick();
        loginService.nameFieldSendKeys(USER_NAME);
        loginService.passwordFieldClick();
        loginService.passwordFieldSendKeys(PASSWORD);
        loginService.submitButtonClick();
    }


    @AfterTest(description = "Stop Browser")
    public void stopBrowser(){
        DriverManager.getInstance().getDriver().quit();
    }
}
