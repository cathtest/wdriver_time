package com.kate.mentoring.java.services;

import com.kate.mentoring.java.blocks.TableJournalBlock;
import com.kate.mentoring.java.logics.FillingTable;

public class FillingActivityServiceSixth extends FillingTable{
    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceSixth() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill() {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='';");
    }
}
