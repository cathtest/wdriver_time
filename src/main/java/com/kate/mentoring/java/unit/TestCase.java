package com.kate.mentoring.java.unit;

import com.kate.mentoring.java.business_objects.UserModel;
import com.kate.mentoring.java.logics.CalendarLogics;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.kate.mentoring.java.services.*;
import com.kate.mentoring.java.utils.LogManager;

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
    private UserModel userModel;
    protected FillingActivityService fillingActivityService;
    protected SmallQuantity smallQuantity;
    protected BigQuantity bigQuantity;



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


    @BeforeMethod (description = "Initializing com.kate.mentoring.java.services", groups = "forth", dependsOnGroups = "third")
    public void initServices(){
        controlPanelService = new ControlPanelService();
        exportExcelService = new ExportExcelService();
        tableJournalService = new TableJournalService();
        calendarService = new CalendarService();
        warningMessagesService = new WarningMessagesService();
        overtimeCancellingService = new OvertimeCancellingService();
        loginService = new LoginService();
        calendarLogics = new CalendarLogics();
        fillingActivityServiceFirst = new FillingActivityServiceFirst();
        fillingActivityServiceSecond = new FillingActivityServiceSecond();
        fillingActivityServiceThird = new FillingActivityServiceThird();
        fillingActivityServiceForth = new FillingActivityServiceForth();
        fillingActivityServiceFifth = new FillingActivityServiceFifth();
        fillingActivityServiceSixth = new FillingActivityServiceSixth();
        fillingActivityService = new FillingActivityService();

    }

    @BeforeSuite(description = "Initializing business objects", groups = "fifth", dependsOnGroups = "third")
    public void initBusinessObjects(){
        try(FileReader reader = new FileReader("keys.properties")){
            Properties properties;
            properties = new Properties();
            properties.load(reader);

            userModel = new UserModel(properties.getProperty("USER_NAME"), properties.getProperty("PASSWORD"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @BeforeMethod(description = "Logging in", groups = "sixth", dependsOnGroups = "fifth")
    public void login(){
        logManager.loggingInfo("Logging in");
        loginService.nameFieldClick();
        loginService.nameFieldSendKeys(userModel.getUsername());
        loginService.passwordFieldClick();
        loginService.passwordFieldSendKeys(userModel.getPassword());
        loginService.submitButtonClick();
    }


    @AfterTest(description = "Stop Browser")
    public void stopBrowser(){
        DriverManager.getInstance().getDriver().quit();
    }
}
