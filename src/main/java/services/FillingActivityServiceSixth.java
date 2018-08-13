package services;

import blocks.TableJournalBlock;
import logics.FillingTable;

public class FillingActivityServiceSixth extends FillingTable{
    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceSixth() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill() {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Test';");
    }
}
