package pages;

import blocks.Calendar;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import wrapped.ButtonCustom;
import wrapped.TextInputCustom;

public class LoginPage extends DefaultPage{

    @FindBy(css = "input#userNameInput.text.fullWidth")
    private TextInputCustom nameField;

    @FindBy(css = "input#passwordInput.text.fullWidth")
    private TextInputCustom passwordField;

    @FindBy(css = "span#submitButton.submit")
    private ButtonCustom submitButton;



    public TextInputCustom getNameField() {
        return nameField;
    }

    public TextInputCustom getPasswordField() {
        return passwordField;
    }

    public ButtonCustom getSubmitButton() {
        return submitButton;
    }
}
