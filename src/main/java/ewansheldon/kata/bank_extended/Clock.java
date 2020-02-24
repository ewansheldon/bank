package ewansheldon.kata.bank_extended;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

    public static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String todayAsString() {
        return dateToday().format(DD_MM_YYYY);
    }

    protected LocalDate dateToday() {
        return LocalDate.now();
    }
}
