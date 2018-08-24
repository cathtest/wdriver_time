package com.kate.mentoring.java.properties;

import com.kate.mentoring.java.enums.ProjectProperties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReaderSingleton {
    private static PropertiesReaderSingleton _instance;
    private Properties properties;

    public static PropertiesReaderSingleton getInstance(){
        if (_instance == null) {
            _instance = new PropertiesReaderSingleton();
        }
        return _instance;
    }

    private FileReader reader;

    private void createReader(String fileName){
        try {
            this.reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileReader getReader(){
        properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public String getValue(String value){
        PropertiesReaderSingleton.getInstance().createReader("/Users/katsiaryna_hrynchuk/Documents/wdriver_time-9_to_check/src/test/resources/properties/keys.properties");
        getReader();
        return properties.getProperty(value);
    }
}
