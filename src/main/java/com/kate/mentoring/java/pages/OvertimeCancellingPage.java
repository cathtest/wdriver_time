package com.kate.mentoring.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import com.kate.mentoring.java.wrapped.ButtonCustom;

public class OvertimeCancellingPage extends DefaultPage {

    @FindBy(css = "footer>button.green.button")
    private ButtonCustom cancelOvertimeButton;


    public WebElement getCancelOvertimeButton() {
        return cancelOvertimeButton;
    }
}
