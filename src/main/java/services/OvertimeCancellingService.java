package services;

import pages.OvertimeCancellingPage;

public class OvertimeCancellingService {

    private OvertimeCancellingPage overtimeCancellingPage;

    public OvertimeCancellingService() {
        this.overtimeCancellingPage = new OvertimeCancellingPage();
    }

    private OvertimeCancellingPage getOvertimeCancellingPage(){
        return overtimeCancellingPage;
    }

    public void cancelOvertimeButtonClick(){
        overtimeCancellingPage.getCancelOvertimeButton().click();
    }
}
