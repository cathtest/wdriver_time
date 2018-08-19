package com.kate.mentoring.java.unit;

import com.kate.mentoring.java.business_objects.UserModel;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.logics.CalendarLogics;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.kate.mentoring.java.services.*;
import com.kate.mentoring.java.utils.LogManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestCase {

    private String startURL;
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
    protected Quantity quantity;




    @BeforeSuite(description = "Starting Logger")
    public void startLogger(){
        logManager = new LogManager();
    }

    @BeforeSuite(description = "Initializing business objects")
    public void initBusinessObjects(){
        Properties properties;
        properties = new Properties();
        PropertiesReaderSingleton.getInstance().createReader("src\\test\\resources\\keys.properties");
        try {
            properties.load(PropertiesReaderSingleton.getInstance().getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }

        userModel = new UserModel(ProjectProperties.USER_NAME.getProperty(), ProjectProperties.PASSWORD.getProperty());
    }

    @BeforeMethod (description = "Initializing variables from properties", groups = "first")
    public void initProperties(){
        try(FileReader reader = new FileReader("src\\test\\resources\\keys.properties")){
            properties = new Properties();
            properties.load(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
        startURL = ProjectProperties.START_URL.getProperty();
        driverType = ProjectProperties.driverType.getProperty();
        driverPath = ProjectProperties.driverPath.getProperty();
    }

    @BeforeMethod (description = "Starting driver", groups = "second", dependsOnGroups = "first")
    public void startDriver(){
        logManager.loggingInfo("Start browser");
        System.setProperty(driverType, driverPath);
        DriverManager.getInstance().createDriver();
        DriverManager.getInstance().getDriver().manage().deleteAllCookies();
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        logManager.loggingInfo("Browser started");
        DriverManager.getInstance().getDriver().get(startURL);
        DriverManager.getInstance().getDriver().manage().window().maximize();
    }


    @BeforeMethod (description = "Initializing com.kate.mentoring.java.services", groups = "third", dependsOnGroups = "second")
    public void initServices(){
        controlPanelService = new ControlPanelService();
        exportExcelService = new ExportExcelService();
        tableJournalService = new TableJournalService();
        calendarService = new CalendarService();
        warningMessagesService = new WarningMessagesService();
        overtimeCancellingService = new OvertimeCancellingService();
        loginService = new LoginService();
        calendarLogics = new CalendarLogics();
        fillingActivityService = new FillingActivityService();
        quantity = new Quantity();

    }


    @BeforeMethod(description = "Logging in", groups = "fifth", dependsOnGroups = "third")
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
