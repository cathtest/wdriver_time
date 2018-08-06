package pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import unit.DriverManager;
import utils.Sleeper;

public class DefaultPage {

    public DefaultPage() {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), this);
    }

//    public void click(WebElement we){
//        highlightElement(we);
//        we.click();
//        Sleeper.sleep(2);
//    }
//
//    public void highlightElement(WebElement w) {
//        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript("arguments[0].style.border='2px solid red'", w);
//    }
}
