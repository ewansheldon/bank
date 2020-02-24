package ewansheldon.kata.bank_extended;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClockShould {
    @Test
    void return_date_formatted_as_ddmmyyyy() {
        Clock clock = new TestableClock();
        assertEquals("12/10/2019", clock.todayAsString());
    }

    class TestableClock extends Clock {
        @Override
        protected LocalDate dateToday() {
            return LocalDate.of(2019, 10, 12);
        }
    }
}