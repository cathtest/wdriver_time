package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

public class ExportExcelPage extends DefaultPage {

    @FindBy (xpath = "//footer/span[2]")
    private WebElement exportButton;

    @FindBy (xpath = "//div[@class='date-input-block'][1]")
    private WebElement rightCalendar;


    public ExportExcelPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getExportButton() {
        return exportButton;
    }

    public WebElement getRightCalendar() {
        return rightCalendar;
    }
}