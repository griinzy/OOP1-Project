package bg.tu_varna.sit.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate parse(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }
}
