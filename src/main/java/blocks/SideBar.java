package blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.DefaultPage;

public class SideBar extends DefaultPage {

    @FindBy (xpath = "//div[@class='_10WwCp07IhP3rR2owZ4h6y']/span[text()='Learn']")
    private  WebElement learn;

    @FindBy (id = "global_menu_toggle")
    private WebElement sideBarIcon;

    @FindBy(css = "input._2z1aEdM3EQapcs4ncnlM-z")
    private WebElement searchField;

    public WebElement getLearn() {
        return learn;
    }

    public WebElement getSideBarIcon(){
        return sideBarIcon;
    }

    public WebElement getSearchField(){
        return searchField;
    }
}
