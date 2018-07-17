package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends DefaultPage {

    @FindBy(css = "input#userNameInput.text.fullWidth")
    private WebElement nameField;

    @FindBy(css = "input#passwordInput.text.fullWidth")
    private WebElement passwordField;

    @FindBy(css = "span#submitButton.submit")
    private WebElement submitButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }
}
