package unit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    void createDriver(){
        try {
            this.driver =  new RemoteWebDriver(new URL("http://127.0.0.1:4445/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver(){
        return driver;
    }
}