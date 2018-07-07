package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExportExcellBlock {
    private WebDriver driver;
    private By exportButton = By.xpath("//span[@class = '' and contains (text(), 'Export')]");

    public ExportExcellBlock(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
}
