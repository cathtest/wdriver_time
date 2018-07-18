package services;

import pages.ExportExcelPage;
import unit.TestCase;

public class ExportExcelService  {

    public ExportExcelService() {
        this.page = new ExportExcelPage(TestCase.driver);
    }

    private ExportExcelPage page;

    private ExportExcelPage getPage(){
        return page;
    }


    //action

    //click
    //tap

    //get text
    //scroll

    //get status checked is


    public void exportButtonClick(){
        getPage().getExportButton().click();
        //impl
    }
}
