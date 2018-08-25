package com.kate.mentoring.java.pages;

import com.kate.mentoring.java.unit.DriverManager;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;


public class DefaultPage {

    public DefaultPage() {
        HtmlElementLoader.populatePageObject(this, DriverManager.getInstance().getDriver());

    }
}