package com.kate.mentoring.java.logics;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.kate.mentoring.java.unit.DriverManager;

public class Fill {
    public void fillJSE(WebElement we, String script) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript(script, we);
    }
}
