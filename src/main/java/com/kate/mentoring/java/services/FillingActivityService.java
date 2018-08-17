package services;

import blocks.TableJournalBlock;
import com.kate.mentoring.java.logics.FillingTable;

public class FillingActivityServiceSeventh extends FillingTable{
    private TableJournalBlock tableJournalBlock;

    public FillingActivityServiceSeventh() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    public void fill(String value) {
        fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Charles';");
    }
}
