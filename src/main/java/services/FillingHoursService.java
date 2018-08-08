package services;

import blocks.TableJournalBlock;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import unit.DriverManager;

public class FillingHoursService {

    private TableJournalBlock tableJournalBlock;

    public FillingHoursService() {
        this.tableJournalBlock = new TableJournalBlock();
    }

    public void fillCellsJSE(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='0.5';", we);
    }

    public void fillCells(){
        tableJournalBlock.getWorkingDaysList().forEach((cell -> {
            fillCellsJSE(cell);
        }));
    }
}
