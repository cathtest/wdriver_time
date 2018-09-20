package com.kate.mentoring.java.wrapped;

import com.kate.mentoring.java.clickerStrategy.IClickerStrategy;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.factory.ClickerFactory;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class Block extends TypifiedElement{
    private IClickerStrategy clickerStrategy;

    public Block(final WebElement wrappedElement) {
        super(wrappedElement);
        this.clickerStrategy = ClickerFactory.getClickerStrategy(ProjectProperties.HIGHLIGHTER.getValue());
    }

    public void click(){
        clickerStrategy.click(getWrappedElement());
    }
}
