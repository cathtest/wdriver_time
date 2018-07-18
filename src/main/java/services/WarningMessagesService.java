package services;

import blocks.WarningMessagesBlock;
import unit.TestCase;

public class WarningMessagesService {

    private WarningMessagesBlock warningMessagesBlock;

    public WarningMessagesService() {
        this.warningMessagesBlock = new WarningMessagesBlock(TestCase.driver);
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
