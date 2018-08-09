package utils;
import java.util.logging.Logger;

public class LogManager {

    public void loggingInfo(String msg) {
        Logger.getLogger(this.getClass().getName()).info(msg);
    }

    public String loggingSevere(String msg) {
        Logger.getLogger(this.getClass().getName()).severe(msg);
        return msg;
    }
}
