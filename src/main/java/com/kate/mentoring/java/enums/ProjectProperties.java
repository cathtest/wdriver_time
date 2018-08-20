package com.kate.mentoring.java.enums;

import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import com.kate.mentoring.java.unit.TestCase;

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

    ProjectProperties(String value){
        PropertiesReaderSingleton.getInstance().createReader("src\\test\\resources\\keys.properties");
        this.value = PropertiesReaderSingleton.getInstance().getValue(value);
    }

    public String getValue() {
        return value;
    }
}
