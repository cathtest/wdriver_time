package wrapped;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class Block extends TypifiedElement{

    protected Block(final WebElement wrappedElement) {
        super(wrappedElement);
    }
}
