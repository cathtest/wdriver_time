package logics;

import blocks.Calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarLogics {

    private Calendar calendar;

    public CalendarLogics(Calendar calendar) {
        this.calendar = calendar;
    }

    public void openYearView(){
        calendar.getAnotherView().click();
    }

    public void openMonthView(){
        calendar.getAnotherView().click();
    }

    public void openYear(int yearChosen){
        String textFromPeriod = calendar.getAnotherView().getText();
        String startYearStr = textFromPeriod.substring(0, 4);
        String endYearStr = textFromPeriod.substring(5, 9);
        int startYearInt = extractInt(startYearStr);
        int endYearInt = extractInt(endYearStr);
        if(endYearInt<yearChosen && yearChosen>startYearInt){
            for(int i=yearChosen; endYearInt<i && i>startYearInt; i++){
                calendar.getRightArrow().click();
                calendar.getAnotherView().getText();
            }
        } else if (endYearInt>yearChosen && yearChosen<startYearInt){
            for(int i=yearChosen; endYearInt>i && i<startYearInt; i++){
                calendar.getLeftArrow().click();
                calendar.getAnotherView().getText();
            }
        } else {
            calendar.getAnotherView().getText();
        }

    }


    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }
}
