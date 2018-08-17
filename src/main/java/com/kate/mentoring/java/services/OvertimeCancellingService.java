package services;

import pages.OvertimeCancellingPage;

public class OvertimeCancellingService {

    private OvertimeCancellingPage overtimeCancellingPage;


    public OvertimeCancellingService() {
        this.overtimeCancellingPage = new OvertimeCancellingPage();
    }

    public void cancelOvertimeButtonClick(){
        overtimeCancellingPage.getCancelOvertimeButton().click();
    }
}
