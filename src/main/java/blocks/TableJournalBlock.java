package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableJournalBlock {

    private WebDriver driver;

    private By workingDays = By.xpath("//div[@class='table-week']/div[not(contains(@class, 'not-workday'))]/input");

    private By todayBtn = null;

    private By calendarContainerBtn = null;

    public TableJournalBlock(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getWorkingDaysList() {
        return driver.findElements(workingDays);
    }
}
