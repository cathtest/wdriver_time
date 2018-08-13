package wrapped;

import logics.ClickImplementation;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class Arrow extends TypifiedElement {

    ClickImplementation clickImplementation;


    public Arrow(WebElement wrappedElement) {
        super(wrappedElement);
        this.clickImplementation = new ClickImplementation();
    }

    public void click(){
        clickImplementation.click(getWrappedElement());
    }
}


