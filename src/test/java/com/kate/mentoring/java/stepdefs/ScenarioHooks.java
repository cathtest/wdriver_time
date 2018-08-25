package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.concurrent.TimeUnit;

public class ScenarioHooks {

    private String driverType;
    private String driverPath;
    protected LogManager logManager;


    @Before(order=1)
    public void startLogger(){
        logManager = new LogManager();
    }

    @Before(order=2)
    public void initProperties(){
        driverType = PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.DRIVER_TYPE.getValue());
        driverPath = PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.DRIVER_PATH.getValue());
    }

    @Before(order=3)
    public void startDriver(){
        logManager.loggingInfo("Start browser");
        System.setProperty(driverType, driverPath);
        DriverManager.getInstance().createDriver();
        DriverManager.getInstance().getDriver().manage().deleteAllCookies();
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        logManager.loggingInfo("Browser started");
    }


    @After("@Stop Browser")
    public void stopBrowser(){
        DriverManager.getInstance().getDriver().quit();
    }
}

