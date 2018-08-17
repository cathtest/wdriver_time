package wrapped;

import com.kate.mentoring.java.logics.ClickImplementation;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class TableCustom extends TypifiedElement {

    ClickImplementation clickImplementation;


    public TableCustom(WebElement wrappedElement) {
        super(wrappedElement);
        this.clickImplementation = new ClickImplementation();
    }

    public void click(){
        clickImplementation.click(getWrappedElement());
    }
}
