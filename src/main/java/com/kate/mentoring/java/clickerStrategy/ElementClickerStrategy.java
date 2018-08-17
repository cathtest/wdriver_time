package com.kate.mentoring.java.clickerStrategy;

import org.openqa.selenium.WebElement;

public class ElementClickerStrategy implements IClickerStrategy {

    @Override
    public void click(WebElement webElement) {
        webElement.click();
    }
}
