package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginBlock {
    private WebDriver driver;
    private By nameField = By.cssSelector("input#userNameInput.text.fullWidth");
    private By passwordField = By.cssSelector("input#passwordInput.text.fullWidth");
    private By submitButton = By.cssSelector("span#submitButton.submit");

    public LoginBlock(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getNameField() {

        return driver.findElement(nameField);
    }
    public WebElement getPasswordField() {

        return driver.findElement(passwordField);
    }
    public WebElement getSubmitButton() {

        return driver.findElement(submitButton);
    }
}
