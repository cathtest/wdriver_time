package services;

import blocks.WarningMessagesBlock;

public class WarningMessagesService {

    private WarningMessagesBlock warningMessagesBlock;


    public WarningMessagesService() {
        this.warningMessagesBlock = new WarningMessagesBlock();
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
}
