package com.kate.mentoring.java.properties;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class PropertiesReaderSingleton {
    private static PropertiesReaderSingleton _instance;

    public static PropertiesReaderSingleton getInstance(){
        if (_instance == null) {
            _instance = new PropertiesReaderSingleton();
        }
        return _instance;
    }

    private FileReader reader;

    public void createReader(String fileName){
        try {
            this.reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileReader getReader(){
        return reader;
    }
}
