package services;

import blocks.TableJournalBlock;

public class TwoQuantity extends SmallQuantity{

    TableJournalBlock tableJournalBlock;

    public TwoQuantity(){
        this.tableJournalBlock = new TableJournalBlock();
    }

    @Override
    public void fill() {
        tableJournalBlock.getWorkingDaysList().forEach((cell -> {
            fillJSE(cell, "arguments[0].value='2';");
        }));
    }
}

