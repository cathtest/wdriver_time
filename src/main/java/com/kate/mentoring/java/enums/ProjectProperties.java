package com.kate.mentoring.java.enums;

public enum  ProjectProperties {

    START_URL("START_URL"),
    USER_NAME("USER_NAME"),
    PASSWORD("PASSWORD"),
    DRIVER_TYPE("driverType"),
    DRIVER_PATH("driverPath"),
    HIGHLIGHTER("highlighter");

    private String key;

    ProjectProperties(String key){

        this.key = key;
    }

    public String getValue() {
        return key;
    }
}
