package unit;

import business_objects.User;
import logics.ClickImplementation;
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
    protected FillingHoursService fillingHoursService;
    private LoginService loginService;
    protected LogManager logManager;
    private Properties properties;
    private User user;
    protected ClickImplementation clickImplementation;
    protected FillingActivityServiceFirst fillingActivityServiceFirst;
    protected FillingActivityServiceSecond fillingActivityServiceSecond;
    protected FillingActivityServiceThird fillingActivityServiceThird;
    protected FillingActivityServiceForth fillingActivityServiceForth;
    protected FillingActivityServiceFifth fillingActivityServiceFifth;
    protected FillingActivityServiceSixth fillingActivityServiceSixth;
    protected FillingActivityServiceSeventh fillingActivityServiceSeventh;



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
        fillingHoursService = new FillingHoursService();
        clickImplementation = new ClickImplementation();
        fillingActivityServiceFirst = new FillingActivityServiceFirst();
        fillingActivityServiceSecond = new FillingActivityServiceSecond();
        fillingActivityServiceThird = new FillingActivityServiceThird();
        fillingActivityServiceForth = new FillingActivityServiceForth();
        fillingActivityServiceFifth = new FillingActivityServiceFifth();
        fillingActivityServiceSixth = new FillingActivityServiceSixth();
        fillingActivityServiceSeventh = new FillingActivityServiceSeventh();
        fillingHoursService = new FillingHoursService();
    }

    @BeforeMethod(description = "Initializing business objects", groups = "fifth", dependsOnGroups = "third")
    public void initBusinessObjects(){
        user = new User();
    }


    @BeforeMethod(description = "Logging in", groups = "sixth", dependsOnGroups = "fifth")
    public void login(){
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
