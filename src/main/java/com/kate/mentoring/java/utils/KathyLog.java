package com.kate.mentoring.java.utils;


public class KathyLog implements Runnable{

    private static final ThreadLocal<KathyLog> threadLocalScope = new  ThreadLocal<>();

    private static final String DEFAULT_LOGGER_NAME = "com.epam.libg";

    public static void getLogger() {
        threadLocalScope.get();
    }

    public static void setLogger(KathyLog kathyLog) {
        threadLocalScope.set(kathyLog);
    }

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(DEFAULT_LOGGER_NAME);

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void info(Object o){
        logger.info(o);
    }


    @Override
    public void run() {

    }
}
