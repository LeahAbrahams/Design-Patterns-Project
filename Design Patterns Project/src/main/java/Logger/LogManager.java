package Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager implements IObserver {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static LogManager instance;

    private LogManager() {
    }

    public static LogManager getInstance() {
        synchronized (LogManager.class) {
            if (instance == null) {
                instance = new LogManager();
                Informer.getInformer().attach(instance);
            }
            return instance;
        }
    }

    @Override
    public void update(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        WriterLogger.getLogger().log("[LOG] [" + timestamp + "] " + message);
    }
}
