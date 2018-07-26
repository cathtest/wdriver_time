package unit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverManager {

    private static DriverManager _instance;


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
        try {
            this.driver =  new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), cap(browserName));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver(){
        return driver;
    }
}