package blocks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

public class WarningMessagesBlock extends DefaultPage {

    @FindBy(css = "div.notification-right>svg.cancel")
    private WebElement closeButton;

    @FindBy(xpath = "//div/a[@data-index='0']")
    private WebElement goBackToYourPage;


    public WarningMessagesBlock(WebDriver driver) {
        super(driver);
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getGoBackToYourPage() {
        return goBackToYourPage;
    }


}
