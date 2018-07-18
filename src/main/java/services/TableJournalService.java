package services;

import blocks.TableJournalBlock;
import org.openqa.selenium.WebElement;
import unit.TestCase;

public class TableJournalService {

    private TableJournalBlock tableJournalBlock;

    public TableJournalService() {
        this.tableJournalBlock =  new TableJournalBlock(TestCase.driver);
    }

    private TableJournalBlock getTableJournalBlock(){
        return tableJournalBlock;
    }

    public void clickOnTheFirstActivity(){
        tableJournalBlock.getAddActivityButtonList().get(0).click();
    }

    public void sendKeysToTheActivity(String keys){
        tableJournalBlock.getActivityField().sendKeys(keys);
    }

    public void fillCells(String hours){
        tableJournalBlock.getWorkingDaysList().forEach(cell -> {
            cell.sendKeys(hours);
        });
    }

    public String getTextFromTotalHours(){
        return tableJournalBlock.getTotalHours().getText();
    }
    public WebElement getProgetCellIndex(int index){
        return  tableJournalBlock.getProjectNameCellList().get(index);
    }

    public String getTextFromProjectCellWithIndex(int index){
        return getProgetCellIndex(index).getText();
    }

    public WebElement getActivityButtonWithIndex(int index){
        return tableJournalBlock.getAddActivityButtonList().get(index);
    }

    public void clickActivityButtonWithIndex(int index){
         getActivityButtonWithIndex(index).click();
    }
    public void logHoursForToday(String hours){
        tableJournalBlock.getTodayCell().sendKeys(hours);
    }

    public boolean checkWhetherWorkingDayListIsEmpty(){
        return tableJournalBlock.getWorkingDaysList().isEmpty();
    }

    public String getTextFromWorkingDayColumnDate(){
       return tableJournalBlock.getWorkingDayColumnDate().getText();
    }

    public void EmptyCellClick(){
        tableJournalBlock.getEmptyCell().click();
    }

    public void sendHoursToEmptyCell(String hours){
        tableJournalBlock.getEmptyCell().sendKeys(hours);
    }

    public void overtimeModeSwitchOn(){
        tableJournalBlock.getOvertimeMode().click();
    }

    public void overtimeNotEnabledCellClick(){
        tableJournalBlock.getOvertimeNotSubmitedCell().click();
    }

    public void overTimeEnabledClick(){
        tableJournalBlock.getOvertimeEditModeSubmitedCell().click();
    }

    public String getTextFromTotalOverTimeInfo(){
       return tableJournalBlock.getOvertimeTableInfo().getText();
    }
}
