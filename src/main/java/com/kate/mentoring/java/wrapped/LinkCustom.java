package com.kate.mentoring.java.wrapped;

import com.kate.mentoring.java.logics.ClickImplementation;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class LinkCustom extends TypifiedElement {

    ClickImplementation clickImplementation;


    public LinkCustom(WebElement wrappedElement) {
        super(wrappedElement);
        this.clickImplementation = new ClickImplementation();
    }

    public void click(){
        clickImplementation.click(getWrappedElement());
    }
}
