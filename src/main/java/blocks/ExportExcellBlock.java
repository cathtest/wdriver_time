package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExportExcellBlock {
    private WebDriver driver;
    private By exportToExcelButton = By.xpath("//figure[@class='user-photo-container']/../span[@class='export-excel']");
    private By exportButton = By.xpath("//footer/span[2]");

    public ExportExcellBlock(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getExportToExcelButton() {

        return driver.findElement(exportToExcelButton);
    }
    public WebElement getExportButton() {

        return driver.findElement(exportButton);
    }
}
