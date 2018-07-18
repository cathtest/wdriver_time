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
        String endYearStr = textFromPeriod.substring(5, 11);
        int startYearInt = extractInt(startYearStr);
        int endYearInt = extractInt(endYearStr);

        if(startYearInt<yearChosen && yearChosen>endYearInt){
            for(int i=yearChosen; startYearInt<i && i>endYearInt; i++){
                calendar.getRightArrow().click();
                textFromPeriod = calendar.getAnotherView().getText();
                startYearStr = textFromPeriod.substring(0, 4);
                endYearStr = textFromPeriod.substring(5, 11);
                startYearInt = extractInt(startYearStr);
                endYearInt = extractInt(endYearStr);
                System.out.println(startYearInt + " " + endYearInt);
            }
        } else if (endYearInt>yearChosen && yearChosen<startYearInt){
            for(int i=yearChosen; endYearInt>i && i<startYearInt; i++){
                calendar.getLeftArrow().click();
                textFromPeriod = calendar.getAnotherView().getText();
                startYearStr = textFromPeriod.substring(0, 4);
                endYearStr = textFromPeriod.substring(5, 11);
                startYearInt = extractInt(startYearStr);
                endYearInt = extractInt(endYearStr);
            }
        }
        calendar.getYearsList().forEach(year -> {
            int i = extractInt(year.getText());
            if(i == yearChosen){
                year.click();
            }
        });
    }


    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }
}
