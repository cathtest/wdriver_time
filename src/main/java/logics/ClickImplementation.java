package logics;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import unit.DriverManager;
import utils.Sleeper;



public class ClickImplementation {

    public void click(WebElement webElement) {
        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript("arguments[0].style.border='2px solid red'", webElement);
        Sleeper.sleep(1);

        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript("arguments[0].style.border='0px'", webElement);
        Sleeper.sleep(1);

        webElement.click();

    }
}
