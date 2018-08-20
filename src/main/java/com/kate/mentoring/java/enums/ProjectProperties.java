package com.kate.mentoring.java.enums;

import com.kate.mentoring.java.properties.PropertiesReaderSingleton;

import java.io.IOException;
import java.util.Properties;

public enum  ProjectProperties {

    START_URL("START_URL"),
    USER_NAME("USER_NAME"),
    PASSWORD("PASSWORD"),
    DRIVER_TYPE("driverType"),
    DRIVER_PATH("driverPath"),
    HIGHLIGHTER("highlighter");

    private String value;
    Properties properties;

    ProjectProperties(String value){
        properties = new Properties();
        PropertiesReaderSingleton.getInstance().createReader("src\\test\\resources\\keys.properties");
        try {
            properties.load(PropertiesReaderSingleton.getInstance().getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.value = properties.getProperty(value);
    }


    public String getValue() {
        return value;
    }
}
