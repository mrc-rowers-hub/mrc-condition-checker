package com.mersey.rowing.club.condition_checker.controller.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtil {
    private final LocalDate dateToday = LocalDate.now();
    private final LocalDate dateTomorrow = LocalDate.now().plusDays(1);
    private final static String sixMorning = "06:00";
    private final static String sixEvening = "18:00";
    private final LocalTime currentTime = LocalTime.now();
    private static final ZoneId zoneId = ZoneId.of("Europe/London");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // think about try catches later and what to do in case of invalid input

    public static long callApiDateAndTimeSupplied(String date, String time) {
        log.info("both date and time supplied");
        // Call API with (date, time)
        return getEpochTimeAsLong(date, time);
    }

    public static long[] callApiTimeOnlyIsNull(String date) { // yes
        log.info("time only is null");

        // Take in datetime string in the format "dd/MM/yyyy HH:mm"
        LocalDateTime dateTimeMorning = LocalDateTime.parse(date + " " + sixMorning, dtf);
        long morningEpoch = dateTimeMorning.atZone(zoneId).toInstant().toEpochMilli();

        LocalDateTime dateTimeEvening = LocalDateTime.parse(date + " " + sixEvening, dtf);
        long eveningEpoch = dateTimeEvening.atZone(zoneId).toInstant().toEpochMilli();

        return new long[] {morningEpoch, eveningEpoch};
    }

    public static long getEpochTimeAsLong(String date, String time) {
        LocalDateTime dateTime1 = LocalDateTime.parse(date + " " + time, dtf);
        return dateTime1.atZone(zoneId).toInstant().toEpochMilli();
    }

    public static void callApiDateOnlyIsNull(String time) { // yes
        log.info("date only is null");
        // Call API with (dateToday, time)
    }

    public static void callApiDateNullAndTimeNull(LocalTime currentTime, LocalTime sixMorning, LocalTime sixEvening) { // yes to this
        // Needs splitting up
        log.info("date and time are null OR time is null and date == dateToday");
        // Check to see if current time is after 6AM
        if (currentTime.isAfter(sixMorning) && currentTime.isBefore(sixEvening)) {
            log.info("current time is: " + currentTime);
            // Call API with (dateToday, sixEvening)
            // Call API with (dateTomorrow, sixMorning)
        } else if (currentTime.isBefore(sixMorning)) {
            // Call API with (dateToday, sixMorning)
            // Call API with (dateToday, sixEvening)
        } else {
            // Call API with (dateTomorrow, sixMorning
            // Call API with (dateTomorrow, sixEvening
        }
    }
//    public static void getEpochTime()
}
