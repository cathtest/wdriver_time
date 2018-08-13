package services;

import blocks.TableJournalBlock;
import logics.FillingTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import unit.DriverManager;

public class FillingHoursService extends FillingTable{

    private TableJournalBlock tableJournalBlock;

    public FillingHoursService() {
        this.tableJournalBlock = new TableJournalBlock();
    }


    public void fill(){
        tableJournalBlock.getWorkingDaysList().forEach((cell -> {
            fillJSE(cell, "arguments[0].value='0.5';");
        }));
    }
}
