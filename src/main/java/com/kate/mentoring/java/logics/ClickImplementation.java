package com.kate.mentoring.java.logics;

import com.kate.mentoring.java.clickerStrategy.IClickerStrategy;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.factory.ClickerFactory;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import org.openqa.selenium.WebElement;

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

        clickerStategy = ClickerFactory.getClickerStrategy(ProjectProperties.HIGHLIGHTER.getValue());
    }

    public void click(WebElement webElement) {
        clickerStategy.click(webElement);
    }
}
