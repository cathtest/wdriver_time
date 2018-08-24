package com.kate.mentoring.java.services;

import com.kate.mentoring.java.logics.FillingTable;
import com.kate.mentoring.java.pages.TableJournal;

public class Quantity extends FillingTable{

    TableJournal tableJournalBlock;

    public Quantity(){
        this.tableJournalBlock = new TableJournal();
    }

    @Override
    public void fill(String value) {
        tableJournalBlock.getWorkingDaysList().forEach((cell -> {
            fillJSE(cell, "arguments[0].value='"+value+"';");
        }));
    }
}
