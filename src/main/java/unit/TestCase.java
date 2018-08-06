package unit;
import business_objects.User;
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
    private String driverType;
    private String driverPath;

    protected CalendarLogics calendarLogics;
    protected ExportExcelService exportExcelService;
    protected ControlPanelService controlPanelService;
    protected TableJournalService tableJournalService;
    protected CalendarService calendarService;
    protected WarningMessagesService warningMessagesService;
    protected OvertimeCancellingService overtimeCancellingService;
    private LoginService loginService;
    protected LogManager logManager;
    private Properties properties;
    private User user;



    @BeforeMethod (description = "Initializing variables from properties", groups = "first")
    public void initProperties(){
        try(FileReader reader = new FileReader("keys.properties")){
            properties = new Properties();
            properties.load(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
        START_URL = properties.getProperty("START_URL");
        driverType = properties.getProperty("driverType");
        driverPath = properties.getProperty("driverPath");
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
        DriverManager.getInstance().getDriver().manage().deleteAllCookies();
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
        user = new User();
        logManager.loggingInfo("Logging in");
        loginService.nameFieldClick();
        loginService.nameFieldSendKeys(user.getUsername());
        loginService.passwordFieldClick();
        loginService.passwordFieldSendKeys(user.getPassword());
        loginService.submitButtonClick();
    }


    @AfterTest(description = "Stop Browser")
    public void stopBrowser(){
        DriverManager.getInstance().getDriver().quit();
    }
}
