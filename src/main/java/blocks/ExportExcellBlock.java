package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExportExcellBlock {
    private WebDriver driver;
    private By exportToExcelButton = By.xpath("//figure[@class='user-photo-container']/../span[@class='export-excel']");
    private By exportButton = By.xpath("//footer/span[2]");
    private By rightCalendar = By.xpath("//div[@class='date-input-block'][1]");

    public ExportExcellBlock(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getExportToExcelButton() {

        return driver.findElement(exportToExcelButton);
    }
    public WebElement getExportButton() {

        return driver.findElement(exportButton);
    }
    public WebElement getRightCalendar() {

        return driver.findElement(rightCalendar);
    }
}
