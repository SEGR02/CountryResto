package ar.com.country.restaurant.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationTimeUtils {

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getLocalDate() {
        return getLocalDateTime().toLocalDate();
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date getCurrentDate() {
        return Date.from(Instant.ofEpochSecond(System.currentTimeMillis()));
    }

    public static Date getExpirationDate(int expiration) {
        return Date.from(Instant.ofEpochSecond(System.currentTimeMillis() + (long) expiration * 1000 * 60));
    }

    public static String getTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getLocalDateTime().format(formatter);
    }
}