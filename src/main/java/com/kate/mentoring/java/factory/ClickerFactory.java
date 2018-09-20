package com.kate.mentoring.java.factory;

import com.kate.mentoring.java.clickerStrategy.ElementClickerStrategy;
import com.kate.mentoring.java.clickerStrategy.HighlighterClickerStrategy;
import com.kate.mentoring.java.clickerStrategy.IClickerStrategy;

public class ClickerFactory {

    public static IClickerStrategy getClickerStrategy(String type){

        if(Boolean.parseBoolean(type)){
            return new HighlighterClickerStrategy();
        }
        else {
            return new ElementClickerStrategy();
        }
    }
}
