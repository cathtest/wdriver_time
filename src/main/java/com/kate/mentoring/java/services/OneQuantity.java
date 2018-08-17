package com.kate.mentoring.java.services;

import com.kate.mentoring.java.blocks.TableJournalBlock;

public class OneQuantity extends SmallQuantity{

    TableJournalBlock tableJournalBlock;

    public OneQuantity(){
        this.tableJournalBlock = new TableJournalBlock();
    }

    @Override
    public void fill() {
        tableJournalBlock.getWorkingDaysList().forEach((cell -> {
            fillJSE(cell, "arguments[0].value='1';");
        }));
    }
}
