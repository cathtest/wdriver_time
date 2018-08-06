package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.awt.*;

public class LoginPage extends DefaultPage{

    @FindBy(css = "input#userNameInput.text.fullWidth")
    private TextInput nameField;

    @FindBy(css = "input#passwordInput.text.fullWidth")
    private TextInput passwordField;

    @FindBy(css = "span#submitButton.submit")
    private Button submitButton;

    public TextInput getNameField() {
        return nameField;
    }

    public TextInput getPasswordField() {
        return passwordField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}
