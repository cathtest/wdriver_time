package com.kate.mentoring.java.enums;

import com.kate.mentoring.java.properties.PropertiesReaderSingleton;

import java.io.IOException;
import java.util.Properties;

public enum  ProjectProperties {

    START_URL("START_URL"),
    USER_NAME("USER_NAME"),
    PASSWORD("PASSWORD"),
    driverType("driverType"),
    driverPath("driverPath"),
    highlighter("highlighter");

    private String property;
    Properties properties;

    ProjectProperties(String property){
        properties = new Properties();
        PropertiesReaderSingleton.getInstance().createReader("src\\test\\resources\\keys.properties");
        try {
            properties.load(PropertiesReaderSingleton.getInstance().getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.property = properties.getProperty(property);
    }


    public String getProperty() {
        return property;
    }
}
