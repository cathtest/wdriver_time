package com.kate.mentoring.java.enums;

public enum ActivityNames {

    DAILY_MEET("Daily meet"),

    TEST("Test"),

    BUGS_LOGGING("Bugs logging"),

    ENVIRONMENT_SETUP("Environment setup"),

    REQUIREMENTS("Requirements"),

    CHARLES("Charles"),

    COMMUNICATION("Communication");

    private String value;

    ActivityNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
