package blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class WarningMessagesBlock extends DefaultPage {

    @FindBy(css = "div.notification-right>svg.cancel")
    private Button closeButton;

    @FindBy(xpath = "//div/a[@data-index='0']")
    private Link goBackToYourPage;

    @FindBy(css = "div.notification-left")
    private TextBlock warningMessage;


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
