package com.kate.mentoring.java.services;

import com.kate.mentoring.java.pages.WarningMessages;

public class WarningMessagesService {

    private WarningMessages warningMessagesBlock;


    public WarningMessagesService() {
        this.warningMessagesBlock = new WarningMessages();
    }


    public void clickToCloseWarningMesssage(){
        warningMessagesBlock.getCloseButton().click();
    }

    public void clickToReturnToCurrentUserPage(){
        warningMessagesBlock.getGoBackToYourPage().click();
    }

    public boolean checkWarningMessageIsDisplayed(){
        return warningMessagesBlock.getWarningMessage().isDisplayed();
    }

    public boolean checkModalWindowIsDisplayed(){
        return warningMessagesBlock.getModalWindow().isDisplayed();
    }
}
