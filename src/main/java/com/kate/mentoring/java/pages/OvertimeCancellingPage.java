package com.kate.mentoring.java.pages;

import com.kate.mentoring.java.wrapped.ButtonCustom;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OvertimeCancellingPage extends DefaultPage {

    @FindBy(css = "footer>button.green.button")
    private ButtonCustom cancelOvertimeButton;


    public WebElement getCancelOvertimeButton() {
        return cancelOvertimeButton;
    }
}
