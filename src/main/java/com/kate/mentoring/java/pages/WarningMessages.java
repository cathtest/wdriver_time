package com.kate.mentoring.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.kate.mentoring.java.pages.DefaultPage;
import com.kate.mentoring.java.wrapped.Block;
import com.kate.mentoring.java.wrapped.ButtonCustom;
import com.kate.mentoring.java.wrapped.LinkCustom;
import com.kate.mentoring.java.wrapped.TextBlockCustom;

public class WarningMessages extends DefaultPage {

    @FindBy(css = "div.notification-right>svg.cancel")
    private ButtonCustom closeButton;

    @FindBy(xpath = "//div/a[@data-index='0']")
    private LinkCustom goBackToYourPage;

    @FindBy(css = "div.notification-left")
    private TextBlockCustom warningMessage;

    @FindBy(css = "div.modal-window.open")
    private Block modalWindow;


    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getGoBackToYourPage() {
        return goBackToYourPage;
    }

    public WebElement getWarningMessage() {
        return warningMessage;
    }

    public WebElement getModalWindow(){
        return modalWindow;
    }
}
