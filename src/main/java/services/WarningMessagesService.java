package services;

import blocks.WarningMessagesBlock;

public class WarningMessagesService {

    private WarningMessagesBlock warningMessagesBlock;

    public WarningMessagesService() {
        this.warningMessagesBlock = new WarningMessagesBlock();
    }

    private WarningMessagesBlock getWarningMessagesBlock(){
        return warningMessagesBlock;
    }

    public void clickToCloseWarningMesssage(){
        warningMessagesBlock.getCloseButton().click();
    }

    public void clickToReturnToCurrentUserPage(){
        warningMessagesBlock.getGoBackToYourPage().click();
    }
}
