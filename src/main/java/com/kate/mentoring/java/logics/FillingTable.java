package com.kate.mentoring.java.logics;

import com.kate.mentoring.java.unit.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public abstract class FillingTable {
    public void fillJSE(WebElement we, String script) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript(script, we);
    }

    public abstract void fill(String value);


}

