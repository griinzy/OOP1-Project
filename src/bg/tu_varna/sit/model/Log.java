package bg.tu_varna.sit.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Съхранява историята на промените в склада
 */
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

    /**
     * Добавя нов запис
     * @param entry записът за добавяне
     */
    public void add(LogEntry entry) {
        entries.add(entry);
    }

    /**
     * @return списък с всички записи
     */
    public List<LogEntry> getEntries() {
        return entries;
    }

    /**
     * @param from начална дата на търсене
     * @param to крайна дата на търсене
     * @return списък с всички записи между две зададени дати
     */
    public List<LogEntry> getEntriesBetweenDates(LocalDate from, LocalDate to) {
        return entries.stream().filter(e -> !e.getDate().isBefore(from) && !e.getDate().isAfter(to)).toList();
    }
}
