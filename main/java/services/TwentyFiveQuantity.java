package services;

import blocks.TableJournalBlock;

public class TwentyFiveQuantity extends BigQuantity{

    TableJournalBlock tableJournalBlock;

    public TwentyFiveQuantity(){
        this.tableJournalBlock = new TableJournalBlock();
    }

    @Override
    public void fill() {
        tableJournalBlock.getWorkingDaysList().forEach((cell -> {
            fillJSE(cell, "arguments[0].value='25';");
        }));
    }
}
