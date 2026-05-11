package bg.tu_varna.sit.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private static Log instance;
    private List<LogEntry> entries = new ArrayList<>();

    private Log() {}

    public static Log getInstance() {
        if(instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void add(LogEntry entry) {
        entries.add(entry);
    }

    public List<LogEntry> getEntries() {
        return entries;
    }

    public List<LogEntry> getEntriesBetweenDates(LocalDate from, LocalDate to) {
        return entries.stream().filter(e -> !e.getDate().isBefore(from) && !e.getDate().isAfter(to)).toList();
    }
}
