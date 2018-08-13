package services;

import blocks.TableJournalBlock;
import logics.FillingTable;

public class FillingActivityServiceSecond extends FillingTable{
    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceSecond() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill() {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Daily meet';");
    }
}
