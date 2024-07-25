package com.mersey.rowing.club.condition_checker.controller.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class DateUtil {

  private static final String sixMorning = "06:00";
  private static final String sixEvening = "18:00";
  private static final ZoneId zoneId = ZoneId.of("Europe/London");
  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
  private static final DateTimeFormatter dtfMinusHours = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  @Autowired
  private Clock clock;

  public LocalDate getCurrentDate() {
    return LocalDate.now(clock);
  }

  private LocalTime getCurrentTime() {
    return LocalTime.now(clock);
  }

  public long[] getEpochsDateAndTimeSupplied(String date, String time) {
    log.info("Both date and time supplied");
    log.info("Retrieved epoch time for " + date + " at time " + time);
    return new long[]{getEpochTimeAsLong(date, time)};
  }

  public long[] getEpochsTimeOnlyIsNull(String date) {
    log.info("Time only is null");
    long morningEpoch = getEpochTimeAsLong(date, sixMorning);
    long eveningEpoch = getEpochTimeAsLong(date, sixEvening);
    log.info("Retrieved epoch time for " + date + " at time " + sixMorning);
    log.info("Retrieved epoch time for " + date + " at time " + sixEvening);
    return new long[]{morningEpoch, eveningEpoch};
  }

  public long getEpochTimeAsLong(String date, String time) {
    LocalDateTime dateTime1 = LocalDateTime.parse(date + " " + time, dtf);
    return dateTime1.atZone(zoneId).toInstant().getEpochSecond();
  }

  public long[] getEpochsDateOnlyIsNull(String time) {
    log.info("Date only is null");
    String formattedDateToday = dtfMinusHours.format(getCurrentDate());
    log.info("Retrieved epoch time for " + formattedDateToday + " at time " + time);
    return new long[]{getEpochTimeAsLong(formattedDateToday, time)};
  }

  public long[] getEpochsDateNullAndTimeNull() {
    log.info("date and time are null OR time is null and date == dateToday");
    LocalTime currentTime = getCurrentTime();
    LocalDate dateToday = getCurrentDate();
    LocalDate dateTomorrow = dateToday.plusDays(1);

    String formattedDateToday = dtfMinusHours.format(dateToday);
    String formattedDateTomorrow = dtfMinusHours.format(dateTomorrow);

    log.info("current time is: " + currentTime);

    if (currentTime.isAfter(LocalTime.parse(sixMorning)) && currentTime.isBefore(LocalTime.parse(sixEvening))) {
      long eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");
      long morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
      log.info("Retrieved epoch for " + formattedDateTomorrow + " at sixMorning");
      return new long[]{eveningEpoch, morningEpoch};
    } else if (currentTime.isBefore(LocalTime.parse(sixMorning))) {
      long morningEpoch = getEpochTimeAsLong(formattedDateToday, sixMorning);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixMorning");
      long eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");
      return new long[]{morningEpoch, eveningEpoch};
    } else {
      long morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
      log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixMorning");
      long eveningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixEvening);
      log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixEvening");
      return new long[]{morningEpoch, eveningEpoch};
    }
  }

  public String getDatetimeFromEpochSeconds(Long epoch) {
    Instant instant = Instant.ofEpochSecond(epoch);
    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    return dateTime.format(dtf);
  }
}