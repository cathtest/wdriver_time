package pages;

import org.openqa.selenium.support.PageFactory;
import unit.DriverManager;

public class DefaultPage {

    public DefaultPage() {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), this);
    }
}
