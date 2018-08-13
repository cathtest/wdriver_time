package blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import wrapped.ButtonCustom;
import wrapped.LinkCustom;
import wrapped.TextBlockCustom;

public class WarningMessagesBlock extends DefaultPage {

    @FindBy(css = "div.notification-right>svg.cancel")
    private ButtonCustom closeButton;

    @FindBy(xpath = "//div/a[@data-index='0']")
    private LinkCustom goBackToYourPage;

    @FindBy(css = "div.notification-left")
    private TextBlockCustom warningMessage;


    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getGoBackToYourPage() {
        return goBackToYourPage;
    }

    public WebElement getWarningMessage() {
        return warningMessage;
    }
}
