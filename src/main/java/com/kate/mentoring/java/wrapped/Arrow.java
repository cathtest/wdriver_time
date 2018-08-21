package com.kate.mentoring.java.wrapped;

import com.kate.mentoring.java.clickerStrategy.IClickerStrategy;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.factory.ClickerFactory;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class Arrow extends TypifiedElement {

    private IClickerStrategy clickerStrategy;

    public Arrow(WebElement wrappedElement) {
        super(wrappedElement);
        this.clickerStrategy = ClickerFactory.getClickerStrategy(PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.HIGHLIGHTER.getValue()));
    }

    public void click(){
        clickerStrategy.click(getWrappedElement());
    }
}


