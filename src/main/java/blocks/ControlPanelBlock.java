package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ControlPanelBlock {

    private WebDriver driver;

    private By projectNameCellList = By.xpath("//span[@class='project-name']");


    private By projectList = By.cssSelector("#choose-project-select > section > div> span");

    private By selectProjectBtn = By.id("choose-project-select");

    private By userSelcetorButton = By.xpath("//figure[@class='user-photo-container']/../span[@class='name']");

    private By exportExcellButton = By.xpath("//figure[@class='user-photo-container']/../span[@class='export-excel']");

    private By cellFilled = By.xpath("//div[@class='table-week']//input[@class='changed']");

    private By week1Btn = null;

    private By week2Btn = null;

    private By mounthBtn = null;

    private By cancelBtn = null;

    private By saveBtn = null;

    public ControlPanelBlock(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getCellFilled(){
        return driver.findElement(cellFilled);
    }
}
//input[@class='changed']/../div[@class='table-week']