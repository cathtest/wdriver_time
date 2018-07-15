package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WarningMessages {
    private WebDriver driver;
    private By closeButton = By.cssSelector("div.notification-right>svg.cancel");

    public WarningMessages(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCloseButton() {

        return driver.findElement(closeButton);
    }
}
