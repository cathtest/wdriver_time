package com.kate.mentoring.java.services;

import com.kate.mentoring.java.pages.TableJournal;
import com.kate.mentoring.java.logics.FillingTable;

public class FillingActivityService extends FillingTable{
    private TableJournal tableJournalBlock;

    public FillingActivityService() {
        this.tableJournalBlock =  new TableJournal();
    }

    public void fill(String value) {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='"+value+"';");
    }
}
