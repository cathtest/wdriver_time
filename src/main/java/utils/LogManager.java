package utils;
import java.util.logging.Logger;

public class LogManager {

    public void loggingInfo(String msg) {
        Logger.getLogger(this.getClass().getName()).info(msg);
    }
    public void loggingSevere(String msg) {
        Logger.getLogger(this.getClass().getName()).severe(msg);
    }
}
