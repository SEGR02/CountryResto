package ar.com.country.restaurant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.util.Objects.requireNonNullElse;

@Component
public class DateUtils {
    private static Clock applicationClock;

    public static LocalDate today() {
        return LocalDate.now(requireNonNullElse(applicationClock, Clock.systemDefaultZone()));
    }

    public static LocalDate tomorrow() {
        return today().plusDays(1);
    }

    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MINUTES.between(start, end);
    }

    @Autowired
    public void setClock(Clock clock) {
        DateUtils.applicationClock = clock;
    }

}
