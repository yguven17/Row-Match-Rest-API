package com.rowmatch.util;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil {

    public static boolean isTournamentOpen() {
        LocalTime now = LocalTime.now(ZoneId.of("UTC"));
        LocalTime startTime = LocalTime.of(0, 0);
        LocalTime endTime = LocalTime.of(20, 0);

        return now.isAfter(startTime) && now.isBefore(endTime);
    }

    public static LocalTime now() {
        return LocalTime.now(ZoneId.of("UTC"));
    }
    public static boolean isTournamentStarted() {
        Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        return hour >= 0 && hour < 20;
    }

    public static boolean isTournamentEnded() {
        Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        return hour >= 20;
    }
}
