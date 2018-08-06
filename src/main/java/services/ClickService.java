package services;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import unit.DriverManager;

import java.util.List;


public class ClickService extends EventFiringWebDriver  {

    public ClickService(WebDriver driver) {
        super(driver);
    }


    public void highlightElement(WebElement w) {
        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript("arguments[0].style.border='2px solid red'", w);
    }

    public WebElement getElement(){
        return getElement();
    }

    public void click() {

        highlightElement(getElement());
        getElement().click();
    }
}
