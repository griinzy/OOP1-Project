package bg.tu_varna.sit.model;

import bg.tu_varna.sit.enums.EntryType;

import java.time.LocalDate;

public class LogEntry {
    private EntryType entryType;
    private String productName;
    private double quantity;
    private LocalDate date;

    public LogEntry(EntryType entryType, String productName, double quantity, LocalDate date) {
        this.entryType = entryType;
        this.productName = productName;
        this.quantity = quantity;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return entryType + " " + productName + " " + quantity + " " + date;
    }
}
