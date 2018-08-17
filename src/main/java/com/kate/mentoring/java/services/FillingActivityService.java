package com.kate.mentoring.java.services;

import com.kate.mentoring.java.blocks.TableJournalBlock;
import com.kate.mentoring.java.logics.FillingTable;

public class FillingActivityService extends FillingTable{
    private TableJournalBlock tableJournalBlock;

    public FillingActivityService() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill(String value) {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='"+value+"';");
    }
}
