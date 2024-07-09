package com.mersey.rowing.club.condition_checker.controller.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtil {
    private static final LocalDate dateToday = LocalDate.now();
    private static final LocalDate dateTomorrow = LocalDate.now().plusDays(1);
    private final static String sixMorning = "06:00";
    private final static String sixEvening = "18:00";
    private static final LocalTime currentTime = LocalTime.now();
    private static final ZoneId zoneId = ZoneId.of("Europe/London");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter dtfMinusHours = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static String formattedDateToday = dtfMinusHours.format(dateToday);
    static String formattedDateTomorrow = dtfMinusHours.format(dateTomorrow);
    static long morningEpoch;
    static long eveningEpoch;

    // TODO think about try catches later and what to do in case of invalid input
    // Take in datetime string in the format "dd/MM/yyyy HH:mm"

    public static long[] getEpochsDateAndTimeSupplied(String date, String time) {
        log.info("Both date and time supplied");
        // Return epoch time as a long with (date, time)
        log.info("Retrieved epoch time for " + date + " at time " + time);
        return new long[] {getEpochTimeAsLong(date, time)};
    }

    public static long[] getEpochsTimeOnlyIsNull(String date) {
        log.info("Time only is null");

        // Call getEpochTimeAsLong method with sixMorning, and sixEvening
        morningEpoch = getEpochTimeAsLong(date, sixMorning);
        eveningEpoch = getEpochTimeAsLong(date, sixEvening);

        log.info("Retrieved epoch time for " + date + " at time " + sixMorning);
        log.info("Retrieved epoch time for " + date + " at time " + sixEvening);

        // Return epochs as a list
        return new long[] {morningEpoch, eveningEpoch};
    }

    // Maybe want to make this private
    public static long getEpochTimeAsLong(String date, String time) {
        LocalDateTime dateTime1 = LocalDateTime.parse(date + " " + time, dtf);
        return dateTime1.atZone(zoneId).toInstant().toEpochMilli();
    }

    public static long[] getEpochsDateOnlyIsNull(String time) {
        log.info("Date only is null");
        // Call epochTimeAsLong using dateToday, and the time given
        log.info("Retrieved epoch time for " + formattedDateToday + " at time " + time);
        return new long[] {getEpochTimeAsLong(formattedDateToday, time)};
    }

    public static long[] getEpochsDateNullAndTimeNull() {
        log.info("date and time are null OR time is null and date == dateToday");

        // Check to see if current time is after 6AM
        if (currentTime.isAfter(LocalTime.parse(sixMorning)) && currentTime.isBefore(LocalTime.parse(sixEvening))) {
            log.info("current time is: " + currentTime);
            // Call API with (dateToday, sixEvening)
            eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
            log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");
            // Call API with (dateTomorrow, sixMorning)
            morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
            log.info("Retrieved epoch for " + formattedDateTomorrow + " at sixMorning");

            return new long[] {eveningEpoch, morningEpoch};
        } else if (currentTime.isBefore(LocalTime.parse(sixMorning))) {
            // Call API with (dateToday, sixMorning)
            morningEpoch = getEpochTimeAsLong(formattedDateToday, sixMorning);
            log.info("Retrieved epoch for " + formattedDateToday + " at sixMorning");
            // Call API with (dateToday, sixEvening)
            eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
            log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");

            return new long[] {morningEpoch, eveningEpoch};
        } else {
            // Call API with (dateTomorrow, sixMorning
            morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
            log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixMorning");
            // Call API with (dateTomorrow, sixEvening
            eveningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixEvening);
            log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixEvening");

            return new long[] {morningEpoch, eveningEpoch};
        }
    }
}
