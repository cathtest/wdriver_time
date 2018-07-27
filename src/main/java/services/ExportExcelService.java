package services;

import pages.ExportExcelPage;

public class ExportExcelService  {

    private ExportExcelPage exportExcelPage;

    public ExportExcelService() {
        this.exportExcelPage = new ExportExcelPage();
    }

    private ExportExcelPage getExportExcelPage(){
        return exportExcelPage;
    }

    public void exportButtonClick(){
        getExportExcelPage().getExportButton().click();
    }

    public void rightCalendarClick(){
        exportExcelPage.getRightCalendar().click();
    }

    public boolean checkExportButtonIsEnabled(){
        return exportExcelPage.getExportButton().isEnabled();
    }

    public boolean checkExportBlockIsDisplayed(){
        return exportExcelPage.getExportBlock().isDisplayed();
    }


    //action

    //click
    //tap

    //get text
    //scroll

    //get status checked is


}
