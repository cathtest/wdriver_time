package com.kate.mentoring.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import com.kate.mentoring.java.wrapped.Block;
import com.kate.mentoring.java.wrapped.ButtonCustom;
import com.kate.mentoring.java.wrapped.DateEntity;

public class ExportExcelPage extends DefaultPage{

    @FindBy (xpath = "//footer/span[2]")
    private ButtonCustom exportButton;

    @FindBy (xpath = "//div[@class='date-input-block'][1]")
    private DateEntity rightCalendar;

    @FindBy(css = "div.billing-lock-unlock.billing-lock>header")
    private Block exportBlock;

    public WebElement getExportButton() {
        return exportButton;
    }

    public WebElement getRightCalendar() {
        return rightCalendar;
    }

    public WebElement getExportBlock() {
        return exportBlock;
    }
}
