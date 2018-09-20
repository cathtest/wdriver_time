package com.kate.mentoring.java.properties;

import com.kate.mentoring.java.utils.KathyLog;

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
            KathyLog.error("FileNotFoundException was detected", e);
            e.printStackTrace();
        }
    }

    public FileReader getReader(){
        properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            KathyLog.error("IOException was detected");
            e.printStackTrace();
        }
        return reader;
    }

    public String getValue(String value){
        PropertiesReaderSingleton.getInstance().createReader("src/test/resources/keys.properties");
        getReader();
        return properties.getProperty(value);
    }
}
