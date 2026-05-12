package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.interfaces.Command;
import bg.tu_varna.sit.model.Log;
import bg.tu_varna.sit.model.LogEntry;
import bg.tu_varna.sit.model.Warehouse;
import bg.tu_varna.sit.util.DateParser;

import java.time.LocalDate;
import java.util.List;

/**
 * Извежда справка за всички промени в наличността в периода между две зададени дати.
 */
public class LogCommand implements Command {
    private final Warehouse warehouse = Warehouse.getInstance();
    private final Log log = Log.getInstance();

    @Override
    public String getName() {
        return "log ";
    }

    @Override
    public String getDescription() {
        return "log <from> <to> - Returns a log of all changes in a date range.";
    }

    @Override
    public String execute(String[] args) {
        LocalDate from = DateParser.parse(args[0]);
        LocalDate to = DateParser.parse(args[1]);

        List<LogEntry> entries = log.getEntriesBetweenDates(from, to);

        if(entries.isEmpty()) {
            return "No changes found in range.";
        }
        StringBuilder result = new StringBuilder();
        for(LogEntry entry : entries) {
            result.append(entry).append("\n");
        }
        return result.toString();
    }
}
