package com.kate.mentoring.java.wrapped;

import com.kate.mentoring.java.logics.ClickImplementation;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class TextBlockCustom extends TypifiedElement {

    ClickImplementation clickImplementation;


    public TextBlockCustom(WebElement wrappedElement) {
        super(wrappedElement);
        this.clickImplementation = new ClickImplementation();
    }

    public void click(){
        clickImplementation.click(getWrappedElement());
    }
}
