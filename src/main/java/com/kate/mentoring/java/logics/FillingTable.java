package com.kate.mentoring.java.logics;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.kate.mentoring.java.unit.DriverManager;

public abstract class FillingTable {
    public void fillJSE(WebElement we, String script) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript(script, we);
    }

    public abstract void fill(String value);


}

