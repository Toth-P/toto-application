package data;

import java.time.LocalDate;

/**
 * Data class to parse date.
 */
public class ParseService {
    public static final String REGEX = "\\.";
    public static final String REPLACEMENT = "-";

    public LocalDate getParsedDate(String s) {
        String date = s.replaceAll(REGEX, REPLACEMENT);
        date = date.substring(0, date.length() - 1);
        return LocalDate.parse(date);
    }
}
