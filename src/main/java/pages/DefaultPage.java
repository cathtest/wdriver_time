package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DefaultPage {

    public DefaultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
