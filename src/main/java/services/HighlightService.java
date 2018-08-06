package services;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import unit.DriverManager;
import utils.Sleeper;

public class HighlightService {
    public void highlightElement(WebElement w) {
        ((JavascriptExecutor) DriverManager.getInstance()).executeScript("arguments[0].style.border='2px solid red'", w);
        Sleeper.sleep(1);
    }

    public void unHighlightElement(WebElement w) {
        ((JavascriptExecutor) DriverManager.getInstance()).executeScript("arguments[0].style.border='0px'", w);
        Sleeper.sleep(1);
    }
}
