package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import unit.DriverManager;

public class LoginPage extends DefaultPage{

    @FindBy(css = "input#userNameInput.text.fullWidth")
    private WebElement nameField;

    @FindBy(css = "input#passwordInput.text.fullWidth")
    private WebElement passwordField;

    @FindBy(css = "span#submitButton.submit")
    private WebElement submitButton;

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
