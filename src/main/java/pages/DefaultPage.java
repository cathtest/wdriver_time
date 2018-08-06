package pages;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import unit.DriverManager;



public class DefaultPage {


    public DefaultPage() {
        HtmlElementLoader.populatePageObject(this, DriverManager.getInstance().getDriver());
    }
}
