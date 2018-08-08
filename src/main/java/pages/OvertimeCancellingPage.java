package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class OvertimeCancellingPage extends DefaultPage {

    @FindBy(css = "footer>button.green.button")
    private Button cancelOvertimeButton;


    public WebElement getCancelOvertimeButton() {
        return cancelOvertimeButton;
    }
}
