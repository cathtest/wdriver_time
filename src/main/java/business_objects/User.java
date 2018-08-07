package business_objects;

import java.io.FileReader;
import java.util.Properties;

public class User {
    private String USER_NAME;
    private String PASSWORD;
    private Properties properties;



    public void setUpProperties(){
        try(FileReader reader = new FileReader("keys.properties")){
            properties = new Properties();
            properties.load(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getUsername(){
        setUpProperties();
        USER_NAME = properties.getProperty("USER_NAME");
        return USER_NAME;
    }

    public String getPassword(){
        setUpProperties();
        PASSWORD = properties.getProperty("PASSWORD");
        return PASSWORD;
    }
}
