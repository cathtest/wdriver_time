package pages;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import unit.DriverManager;
import wrapped.Block;


public class DefaultPage {
    Block block;

    public DefaultPage() {
        HtmlElementLoader.populatePageObject(this, DriverManager.getInstance().getDriver());
        HtmlElementLoader.createTypifiedElement(Block<>, )
    }
}
