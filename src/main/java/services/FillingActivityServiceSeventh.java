package services;

import blocks.TableJournalBlock;
import logics.FillingTable;

public class FillingActivityServiceSeventh extends FillingTable{
    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceSeventh() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill() {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Charles';");
    }
}
