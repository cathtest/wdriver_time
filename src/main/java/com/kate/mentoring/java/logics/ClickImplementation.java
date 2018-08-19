package com.kate.mentoring.java.logics;

import com.kate.mentoring.java.clickerStrategy.ElementClickerStrategy;
import com.kate.mentoring.java.clickerStrategy.HighlighterClickerStrategy;
import com.kate.mentoring.java.clickerStrategy.IClickerStrategy;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ClickImplementation {

    private IClickerStrategy clickerStategy;

    public ClickImplementation() {
        Properties properties;
        PropertiesReaderSingleton.getInstance().createReader("src\\test\\resources\\keys.properties");
        properties = new Properties();
        try {
            properties.load(PropertiesReaderSingleton.getInstance().getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Boolean.parseBoolean(ProjectProperties.highlighter.getProperty())){
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
