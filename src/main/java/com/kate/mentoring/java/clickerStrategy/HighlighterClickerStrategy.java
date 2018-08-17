package com.kate.mentoring.java.clickerStrategy;

import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.utils.Sleeper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class HighlighterClickerStrategy implements IClickerStrategy {
    @Override
    public void click(WebElement webElement) {
        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript("arguments[0].style.border='2px solid red'", webElement);
        Sleeper.sleep(1);

        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript("arguments[0].style.border='0px'", webElement);
        Sleeper.sleep(1);

        webElement.click();
    }
}