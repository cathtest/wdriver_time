package services;

import blocks.TableJournalBlock;
import logics.FillingTable;

public class FillingActivityServiceFifth extends FillingTable{

        private TableJournalBlock tableJournalBlock;

        public FillingActivityServiceFifth() {
            this.tableJournalBlock =  new TableJournalBlock();
        }

        public void fill() {
            fillJSE(tableJournalBlock.getActivityField(), "arguments[0].value='Communication';");
        }
}
