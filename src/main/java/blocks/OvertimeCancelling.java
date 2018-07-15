package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OvertimeCancelling {
    private WebDriver driver;
    private By cancelOvertimeButton = By.cssSelector("footer>button.green.button");

    public OvertimeCancelling(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getCancelOvertimeButton() {

        return driver.findElement(cancelOvertimeButton);
    }
}
