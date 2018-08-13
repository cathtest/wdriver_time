package services;
import blocks.TableJournalBlock;
import logics.FillingTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import unit.DriverManager;

public class FillingActivityServiceFirst extends FillingTable{

    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceFirst() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill() {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Requirements';");
    }
}
