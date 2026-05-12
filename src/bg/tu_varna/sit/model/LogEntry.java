package bg.tu_varna.sit.model;

import bg.tu_varna.sit.enums.EntryType;

import java.time.LocalDate;

/**
 * Единично събитие за промяна на количеството в склада
 */
public class LogEntry {
    private EntryType entryType;
    private String productName;
    private double quantity;
    private LocalDate date;

    /**
     * @param entryType тип на промяната
     * @param productName име на продукта
     * @param quantity количество
     * @param date дата на промяната
     */
    public LogEntry(EntryType entryType, String productName, double quantity, LocalDate date) {
        this.entryType = entryType;
        this.productName = productName;
        this.quantity = quantity;
        this.date = date;
    }

    /**
     * @return датата на промяната
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return entryType + " " + productName + " " + quantity + " " + date;
    }
}
