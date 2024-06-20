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
    private final LocalTime sixMorning = LocalTime.of(6, 0);
    private final LocalTime sixEvening = LocalTime.of(18,0);
    private final LocalTime currentTime = LocalTime.now();
    private static final ZoneId zoneId = ZoneId.of("Europe/London");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public static long callApiDateAndTimeSupplied(String date, String time) {
        log.info("both date and time supplied");
        // Call API with (date, time)
        LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, dtf);
        return dateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    public static void callApiTimeOnlyIsNull(String date) { // yes
        log.info("time only is null");
        // Call API with (date, sixMorning)
        // Call API with (date, sixEvening)
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
