package unit;

import java.io.FileReader;
import java.util.Properties;

public class StartProperties {

    Properties properties;

    public void setUpProperties(){
        try(FileReader reader = new FileReader("keys.properties")){
            properties = new Properties();
            properties.load(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
