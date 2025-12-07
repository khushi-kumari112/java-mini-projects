package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date/time operations in the library system.
 * Provides standardized date formatting and parsing for both database and display.
 */
public class DateUtil {
    // Database format: ISO 8601 compatible
    private static final DateTimeFormatter DB_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // User-friendly display format
    private static final DateTimeFormatter DISPLAY_FORMATTER =
        DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' hh:mm a");

    // Current timestamp for database
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DB_FORMATTER);
    }

    // Current timestamp for display
    public static String getCurrentDisplayTime() {
        return LocalDateTime.now().format(DISPLAY_FORMATTER);
    }

    // Database string to LocalDateTime
    public static LocalDateTime parseFromDB(String dateString) {
        return LocalDateTime.parse(dateString, DB_FORMATTER);
    }

    // Format any LocalDateTime for display
    public static String formatForDisplay(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "Not available";
        }
        return dateTime.format(DISPLAY_FORMATTER);
    }

    // Format any LocalDateTime for storing in database
    public static String formatForDB(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DB_FORMATTER);
    }
}
