package services;

import blocks.TableJournalBlock;
import logics.FillingTable;

public class FillingActivityServiceThird extends FillingTable{

    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceThird() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill() {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Bugs logging';");
    }
}
