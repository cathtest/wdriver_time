package com.kate.mentoring.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.wrapped.Block;


public class DefaultPage {

    public DefaultPage() {
        HtmlElementLoader.populatePageObject(this, DriverManager.getInstance().getDriver());

    }
}