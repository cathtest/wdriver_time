package com.kate.mentoring.java.unit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static DriverManager _instance;

    public static DriverManager getInstance() {
        if (_instance == null) {
            _instance = new DriverManager();
        }
        return _instance;
    }

    private WebDriver driver;


    void createDriver(){
        this.driver =  new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}