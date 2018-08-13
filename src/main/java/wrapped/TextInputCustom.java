package wrapped;

import logics.ClickImplementation;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class TextInputCustom extends TypifiedElement {

    ClickImplementation clickImplementation;


    public TextInputCustom(WebElement wrappedElement) {
        super(wrappedElement);
        this.clickImplementation = new ClickImplementation();
    }

    public void click(){
        clickImplementation.click(getWrappedElement());
    }
}
