package recipes.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class DateTimeUtil {
    private static LocalDateTime localDateTime = LocalDateTime.now();
    private static final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss:SSS";

    public static String getCurrentDateTime(LocalDateTime dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        return dateTime.format(formatter);
    }
}
