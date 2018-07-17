package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

public class OvertimeCancellingPage extends DefaultPage {
    @FindBy(css = "footer>button.green.button")
    private WebElement cancelOvertimeButton;


    public OvertimeCancellingPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCancelOvertimeButton() {
        return cancelOvertimeButton;
    }
}
