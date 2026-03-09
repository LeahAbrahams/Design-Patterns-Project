package Logger;

import java.io.FileWriter;
import java.io.PrintWriter;

public class WriterLogger {
    private static WriterLogger instance;
    private PrintWriter writer;

    private WriterLogger() {
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true));
        } catch (java.io.IOException e) {
            throw new RuntimeException("Log initialization failed", e);
        }
    }

    public static WriterLogger getLogger() {
        synchronized (WriterLogger.class) {
            if (instance == null) {
                instance = new WriterLogger();
            }
        }
        return instance;
    }

    public void log(String message) {
        writer.println(message);
        writer.flush();
    }

    public void close() {
        writer.close();
    }
}
