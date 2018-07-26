package unit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import sun.security.krb5.internal.crypto.Des;

import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class DriverManager {

    private static DriverManager _instance;
    private  String remoteUrl;

    public static DriverManager getInstance() {
        if (_instance == null) {
            _instance = new DriverManager();
        }
        return _instance;
    }

    private WebDriver driver;

    @Parameters({ "browserName"})
    public DesiredCapabilities cap(String browserName ){
        DesiredCapabilities cap = null;
        if(browserName.equals("chrome")){
            cap = new DesiredCapabilities().chrome();
            cap.setBrowserName("chrome");
        }
        else if(browserName.equals("internet explorer")){
            cap = new DesiredCapabilities().internetExplorer();
            cap.setBrowserName("internet explorer");
        }
        return cap;
    }

    @Parameters({ "browserName"})
    void createDriver(String browserName){

        try(FileReader reader = new FileReader("keys.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            remoteUrl = properties.getProperty("remoteUrl");
        }catch(Exception e){
            e.printStackTrace();
        }


        try {
            this.driver =  new RemoteWebDriver(new URL(remoteUrl), cap(browserName));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver(){
        return driver;
    }
}