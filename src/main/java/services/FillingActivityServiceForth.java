package services;

import blocks.TableJournalBlock;
import logics.FillingTable;

public class FillingActivityServiceForth extends FillingTable {

    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceForth() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill() {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Environment setup';");
    }
}
