package bg.tu_varna.sit.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Клас за форматиране на дати
 */
public class DateParser {
    /**
     * За задаване на формата на датите
     */
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Форматира низ към дата
     * @param dateString Низът за обработване
     * @return Форматираната дата
     */
    public static LocalDate parse(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }
}
