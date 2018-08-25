package com.kate.mentoring.java.pages;

import com.kate.mentoring.java.wrapped.Arrow;
import com.kate.mentoring.java.wrapped.ButtonCustom;
import com.kate.mentoring.java.wrapped.DateEntity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Calendar extends DefaultPage {

    @FindBy(xpath = "//span[@class='date-value']")
    private ButtonCustom calendarButton;

    @FindBy(css = "div.item-day.in-current-month")
    private DateEntity timeRangeNotCurrent;

    @FindBy(css = "span.label")
    private DateEntity anotherView;

    @FindBy(css = "div.year-item.selected+div.year-item")
    private DateEntity theFollowingYear;

    @FindBy(css = "div.month-item:nth-of-type(1)")
    private DateEntity theFirstMonth;

    @FindBy(xpath = "//div[@class='year-item selected']/preceding-sibling::*")
    private DateEntity lastYear;

    @FindBy(css = "article.calendar-row:nth-of-type(3)>div.month-item:nth-of-type(4)")
    private DateEntity lastMonth;

    @FindBy(css = "article.calendar-row:nth-of-type(6)>div.item-day.in-current-month:nth-of-type(7)")
    private DateEntity theFirstDay;

    @FindBy(xpath = "//article[@class='calendar-row'][1]/div[@class='year-item'][1]")
    private DateEntity leftCornerYear;

    @FindBy(css = "span.prev")
    private Arrow leftArrow;

    @FindBy(css = "span.next")
    private Arrow rightArrow;

    @FindBy(css = "div.year-item")
    private List <DateEntity> yearsList;


    public WebElement getCalendarButton() {
        return calendarButton;
    }

    public WebElement getTimeRangeNotCurrent() {
        return timeRangeNotCurrent;
    }

    public WebElement getAnotherView() {
        return anotherView;
    }

    public WebElement getTheFollowingYear() {
        return theFollowingYear;
    }

    public WebElement getTheFirstMonth() {
        return theFirstMonth;
    }

    public WebElement getLastYear() {
        return lastYear;
    }

    public WebElement getTheFirstDay() {
        return theFirstDay;
    }

    public WebElement getLeftArrow() {
        return leftArrow;
    }

    public WebElement getRightArrow() {
        return rightArrow;
    }

    public List<DateEntity> getYearsList() {
        return yearsList;
    }

}
