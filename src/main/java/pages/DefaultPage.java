package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import unit.DriverManager;
import wrapped.Block;


public class DefaultPage {

    public DefaultPage() {
        HtmlElementLoader.populatePageObject(this, DriverManager.getInstance().getDriver());

    }
}