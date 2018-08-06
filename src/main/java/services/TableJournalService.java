package services;

import blocks.TableJournalBlock;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import unit.DriverManager;

public class TableJournalService {

    private TableJournalBlock tableJournalBlock;

    public TableJournalService() {
        this.tableJournalBlock =  new TableJournalBlock();
    }

    private TableJournalBlock getTableJournalBlock(){
        return tableJournalBlock;
    }

    public void clickOnTheFirstActivity(){
        tableJournalBlock.getAddActivityButtonList().get(0).click();
    }

//    public void sendKeysToTheActivity(String keys){
//        tableJournalBlock.getActivityField().sendKeys(keys);
//    }

//    public void fillCells(String hours){
//        tableJournalBlock.getWorkingDaysList().forEach(cell -> {
//            cell.sendKeys(hours);
//        });
//    }

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

    public boolean checkWhetherOverTimeSumbittedCellIsDisplayed (){
        return tableJournalBlock.getOvertimeSubmitedCell().isDisplayed();
    }

    public void sendKeysToTheActivityJSE(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Daily sync-up';", we);
    }

    public void sendKeysToTheActivity(){
        sendKeysToTheActivityJSE(tableJournalBlock.getActivityField());
    }

    public void fillCellsJSE(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='0.5';", we);
    }

    public void fillCells(){
        tableJournalBlock.getWorkingDaysList().forEach((cell -> {
            fillCellsJSE(cell);
        }));
    }
}
