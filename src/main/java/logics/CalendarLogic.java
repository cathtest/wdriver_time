package logics;

import blocks.Calendar;

public class CalendarLogic {

    private Calendar calendar;

    public CalendarLogic(Calendar calendar) {
        this.calendar = calendar;
    }

    public void openYearView(){
        calendar.getAnotherView().click();
    }

    public void openMonthView(){
        calendar.getAnotherView().click();
    }

    public void openYear(int yearChosen){

    }
}
