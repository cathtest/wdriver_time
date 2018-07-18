package services;

import pages.OvertimeCancellingPage;
import unit.TestCase;

public class OvertimeCancellingService {

    private OvertimeCancellingPage overtimeCancellingPage;

    public OvertimeCancellingService() {
        this.overtimeCancellingPage = new OvertimeCancellingPage(TestCase.driver);
    }

    private OvertimeCancellingPage getOvertimeCancellingPage(){
        return overtimeCancellingPage;
    }

    public void cancelOvertimeButtonClick(){
        overtimeCancellingPage.getCancelOvertimeButton().click();
    }
}
