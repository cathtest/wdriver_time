package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OvertimeCancellingPage extends DefaultPage {

    @FindBy(css = "footer>button.green.button")
    private WebElement cancelOvertimeButton;

    public WebElement getCancelOvertimeButton() {
        return cancelOvertimeButton;
    }
}
