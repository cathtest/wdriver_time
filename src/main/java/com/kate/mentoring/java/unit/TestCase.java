package com.kate.mentoring.java.unit;

import com.kate.mentoring.java.business_objects.UserModel;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.logics.CalendarLogics;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.kate.mentoring.java.services.*;
import com.kate.mentoring.java.utils.KathyLog;

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
    protected KathyLog kathyLog;
    private UserModel userModel;
    protected FillingActivityService fillingActivityService;
    protected Quantity quantity;


    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";


    @BeforeSuite(description = "Starting Logger")
    public void startLogger(){
        kathyLog = new KathyLog();
        KathyLog.getLogger();
        KathyLog.setLogger(kathyLog);
        KathyLog.info("Logger here");
        KathyLog.debug("Logger here");
        KathyLog.warn("Logger here");
        KathyLog.error("Logger here");
        KathyLog.trace("Logger here");
    }

    @BeforeSuite(description = "Initializing business objects")
    public void initBusinessObjects(){
        userModel = new UserModel(PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.USER_NAME.getValue()), PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.PASSWORD.getValue()));
    }

    @BeforeMethod (description = "Initializing variables from properties", groups = "first")
    public void initProperties(){
        startURL = PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.START_URL.getValue());
        driverType = PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.DRIVER_TYPE.getValue());
        driverPath = PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.DRIVER_PATH.getValue());
    }

    @BeforeMethod (description = "Starting driver", groups = "second", dependsOnGroups = "first")
    public void startDriver(){
        KathyLog.info("Start browser");
        System.setProperty(driverType, driverPath);
        DriverManager.getInstance().createDriver();
        DriverManager.getInstance().getDriver().manage().deleteAllCookies();
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        KathyLog.info("Browser started");
        DriverManager.getInstance().getDriver().get(startURL);
        DriverManager.getInstance().getDriver().manage().window().maximize();
    }


//    @BeforeSuite
//    private void takeScreenshot() {
//        File screenshot = ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
//        try {
//            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
//            File copy = new File(screenshotName + ".png");
//            FileUtils.copyFile(screenshot, copy);
//            LogManager.info("Saved screenshot: " + screenshotName);
//        } catch (IOException e) {
//
//        }
//        try {
//            ReportPortalMessage message = new ReportPortalMessage(screenshot, "test");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


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
        KathyLog.info("Logging in");
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
