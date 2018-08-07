package services;
import blocks.TableJournalBlock;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import unit.DriverManager;

public class FillingActivityService {

    private TableJournalBlock tableJournalBlock;

    public FillingActivityService() {
        this.tableJournalBlock =  new TableJournalBlock();
    }


    public void sendKeysToTheActivityJSEFirstValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Daily meet';", we);
    }

    public void sendKeysToTheActivityJSESecondValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Bugs logging';", we);
    }

    public void sendKeysToTheActivityJSEThirdValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Environment setup';", we);
    }

    public void sendKeysToTheActivityJSEForthValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Communication';", we);
    }

    public void sendKeysToTheActivityJSEFifthValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Test';", we);
    }

    public void sendKeysToTheActivityJSESixthValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Charles';", we);
    }

    public void sendKeysToTheActivityJSESeventhValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Requirements';", we);
    }

    public void sendKeysToTheActivityJSEEighthValue(WebElement we){
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        jse.executeScript("arguments[0].value='Investigation';", we);
    }

    public void sendKeysToTheActivityFirstValue(){
        sendKeysToTheActivityJSEFirstValue(tableJournalBlock.getActivityField());
    }

    public void sendKeysToTheActivitySecondValue(){
        sendKeysToTheActivityJSESecondValue(tableJournalBlock.getActivityField());
    }

    public void sendKeysToTheActivityThirdValue(){
        sendKeysToTheActivityJSEThirdValue(tableJournalBlock.getActivityField());
    }

    public void sendKeysToTheActivityForthValue(){
        sendKeysToTheActivityJSEForthValue(tableJournalBlock.getActivityField());
    }

    public void sendKeysToTheActivityFifthValue(){
        sendKeysToTheActivityJSEFifthValue(tableJournalBlock.getActivityField());
    }

    public void sendKeysToTheActivitySixthValue(){
        sendKeysToTheActivityJSESixthValue(tableJournalBlock.getActivityField());
    }

    public void sendKeysToTheActivitySeventhValue(){
        sendKeysToTheActivityJSESeventhValue(tableJournalBlock.getActivityField());
    }

    public void sendKeysToTheActivityEighthValue(){
        sendKeysToTheActivityJSEEighthValue(tableJournalBlock.getActivityField());
    }
}
