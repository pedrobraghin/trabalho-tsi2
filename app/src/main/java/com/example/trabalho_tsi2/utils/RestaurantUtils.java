package com.example.trabalho_tsi2.utils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class RestaurantUtils {
    public static final int OPEN_TIME_MORNING = 11;
    public static final int CLOSE_TIME_MORNING = 13;

    public static final int OPEN_TIME_NIGHT = 18;
    public static final int CLOSE_TIME_NIGHT = 20;

    public static boolean isRestaurantOpen() {
        ZoneId matoGrossoDoSulTimeZone = ZoneId.of("America/Campo_Grande");
        ZonedDateTime currentDateTimeMatoGrossoDoSul = ZonedDateTime.now(matoGrossoDoSulTimeZone);
        LocalTime currentTime = currentDateTimeMatoGrossoDoSul.toLocalTime();
        DayOfWeek currentDayOfWeek = currentDateTimeMatoGrossoDoSul.getDayOfWeek();

        if (currentDayOfWeek == DayOfWeek.SUNDAY) return false;
        return isNightAndRestaurantOpen(currentTime) || isMorningAndRestaurantOpen(currentTime);
    }

    public static boolean isMorningAndRestaurantOpen(LocalTime currentTime) {
        LocalTime openTime = LocalTime.of(OPEN_TIME_MORNING -4, 0);
        LocalTime closeTime = LocalTime.of(CLOSE_TIME_MORNING -4, 0);
        return currentTime.isAfter(openTime) && currentTime.isBefore(closeTime);
    }

    public static boolean isNightAndRestaurantOpen(LocalTime currentTime) {
        LocalTime openTime = LocalTime.of(OPEN_TIME_NIGHT -4, 0);
        LocalTime closeTime = LocalTime.of(CLOSE_TIME_NIGHT -4, 0);
        return currentTime.isAfter(openTime) && currentTime.isBefore(closeTime);
    }

    public static String getWeekday(int index) {
        switch (index) {
            case 0:
                return "Segunda";
            case 1:
                return "Terça";
            case 2:
                return "Quarta";
            case 3:
                return "Quinta";
            case 4:
                return "Sexta";
            case 5:
                return "Sábado";
            case 6:
                return "Domingo";
            default:
                return "Not found";
        }
    }
}
