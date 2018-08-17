package com.kate.mentoring.java.logics;

import com.kate.mentoring.java.clickerStrategy.ElementClickerStrategy;
import com.kate.mentoring.java.clickerStrategy.HighlighterClickerStrategy;
import com.kate.mentoring.java.clickerStrategy.IClickerStrategy;
import org.openqa.selenium.WebElement;

import java.io.FileReader;
import java.util.Properties;


public class ClickImplementation {

    private IClickerStrategy clickerStategy;

    public ClickImplementation() {
        Properties properties;
        FileReader reader = new FileReader("keys.properties"))//todo
        properties = new Properties();
        properties.load(reader);

        if(Boolean.parseBoolean(properties.getProperty("is.use.highlighter"))){
            clickerStategy = new HighlighterClickerStrategy();
        }
        else {
            clickerStategy = new ElementClickerStrategy();
        }
    }

    public void click(WebElement webElement) {
        clickerStategy.click(webElement);
    }
}
