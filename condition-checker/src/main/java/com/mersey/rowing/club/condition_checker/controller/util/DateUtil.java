package com.mersey.rowing.club.condition_checker.controller.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DateUtil {
  // todo AS LIST REPLACEMENTS TODAY
  private static final String sixMorning = "06:00";
  private static final String sixEvening = "18:00";
  private static final ZoneId zoneId = ZoneId.of("Europe/London");
  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
  private static final DateTimeFormatter dtfMinusHours = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final long ONE_HOUR_EPOCH = 3600L;

  @Autowired private Clock clock;

  private LocalDate getCurrentDate() {
    return LocalDate.now(clock);
  }

  private LocalTime getCurrentTime() {
    return LocalTime.now(clock);
  }

  public long[] getEpochsDateAndTimeSupplied(String date, String time) {
    log.info("Both date and time supplied");
    log.info("Retrieved epoch time for " + date + " at time " + time);
    return new long[] {getEpochTimeAsLong(date, time)};
  }

  public List<Long> getEpochsDateAndTimeSuppliedASLIST(String date, String time) {
    List<Long> epochsToReturn = new ArrayList<>();

    log.info("Both date and time supplied");
    log.info("Retrieved epoch time for " + date + " at time " + time);
    epochsToReturn.add(getEpochTimeAsLong(date, time));
    return epochsToReturn;
  }

  public long[] getEpochsTimeOnlyIsNull(String date) {
    log.info("Time only is null");
    long morningEpoch = getEpochTimeAsLong(date, sixMorning);
    long eveningEpoch = getEpochTimeAsLong(date, sixEvening);
    log.info("Retrieved epoch time for " + date + " at time " + sixMorning);
    log.info("Retrieved epoch time for " + date + " at time " + sixEvening);
    return new long[] {morningEpoch, eveningEpoch};
  }

  public List<Long> getEpochsTimeOnlyIsNullASLIST(String date) {
    List<Long> epochsToReturn = new ArrayList<>();

    log.info("Time only is null");
    long morningEpoch = getEpochTimeAsLong(date, sixMorning);
    long eveningEpoch = getEpochTimeAsLong(date, sixEvening);
    log.info("Retrieved epoch time for " + date + " at time " + sixMorning);
    log.info("Retrieved epoch time for " + date + " at time " + sixEvening);
    epochsToReturn.add(eveningEpoch);
    epochsToReturn.add(morningEpoch);
    return epochsToReturn;
  }

  public long getEpochTimeAsLong(String date, String time) {
    LocalDateTime dateTime1 = LocalDateTime.parse(date + " " + time, dtf);
    return dateTime1.atZone(zoneId).toInstant().getEpochSecond();
  }

  public long[] getEpochsDateOnlyIsNull(String time) {
    log.info("Date only is null");
    String formattedDateToday = dtfMinusHours.format(getCurrentDate());
    log.info("Retrieved epoch time for " + formattedDateToday + " at time " + time);
    return new long[] {getEpochTimeAsLong(formattedDateToday, time)};
  }

  public List<Long> getEpochsDateOnlyIsNullASLIST(String time) {
    List<Long> epochsToReturn = new ArrayList<>();

    log.info("Date only is null");
    String formattedDateToday = dtfMinusHours.format(getCurrentDate());
    log.info("Retrieved epoch time for " + formattedDateToday + " at time " + time);
    epochsToReturn.add(getEpochTimeAsLong(formattedDateToday, time));
    return epochsToReturn;
  }

  public long[] getEpochsDateNullAndTimeNull() {
    log.info("date and time are null OR time is null and date == dateToday");
    LocalTime currentTime = getCurrentTime();
    LocalDate dateToday = getCurrentDate();
    LocalDate dateTomorrow = dateToday.plusDays(1);

    String formattedDateToday = dtfMinusHours.format(dateToday);
    String formattedDateTomorrow = dtfMinusHours.format(dateTomorrow);

    log.info("current time is: " + currentTime);

    if (currentTime.isAfter(LocalTime.parse(sixMorning))
        && currentTime.isBefore(LocalTime.parse(sixEvening))) {
      long eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");
      long morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
      log.info("Retrieved epoch for " + formattedDateTomorrow + " at sixMorning");
      return new long[] {eveningEpoch, morningEpoch};
    } else if (currentTime.isBefore(LocalTime.parse(sixMorning))) {
      long morningEpoch = getEpochTimeAsLong(formattedDateToday, sixMorning);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixMorning");
      long eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");
      return new long[] {morningEpoch, eveningEpoch};
    } else {
      long morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
      log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixMorning");
      long eveningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixEvening);
      log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixEvening");
      return new long[] {morningEpoch, eveningEpoch};
    }
  }

  public List<Long> getEpochsDateNullAndTimeNullASLIST() {
    List<Long> epochsToReturn = new ArrayList<>();
    log.info("date and time are null OR time is null and date == dateToday");
    LocalTime currentTime = getCurrentTime();
    LocalDate dateToday = getCurrentDate();
    LocalDate dateTomorrow = dateToday.plusDays(1);

    String formattedDateToday = dtfMinusHours.format(dateToday);
    String formattedDateTomorrow = dtfMinusHours.format(dateTomorrow);

    log.info("current time is: " + currentTime);

    if (currentTime.isAfter(LocalTime.parse(sixMorning))
        && currentTime.isBefore(LocalTime.parse(sixEvening))) {
      long eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");
      long morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
      log.info("Retrieved epoch for " + formattedDateTomorrow + " at sixMorning");
      epochsToReturn.add(eveningEpoch);
      epochsToReturn.add(morningEpoch);
      return epochsToReturn;
    } else if (currentTime.isBefore(LocalTime.parse(sixMorning))) {
      long morningEpoch = getEpochTimeAsLong(formattedDateToday, sixMorning);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixMorning");
      long eveningEpoch = getEpochTimeAsLong(formattedDateToday, sixEvening);
      log.info("Retrieved epoch for " + formattedDateToday + " at sixEvening");
      epochsToReturn.add(eveningEpoch);
      epochsToReturn.add(morningEpoch);
      return epochsToReturn;
    } else {
      long morningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixMorning);
      log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixMorning");
      long eveningEpoch = getEpochTimeAsLong(formattedDateTomorrow, sixEvening);
      log.info("Retrieved epoch time for " + formattedDateTomorrow + " at sixEvening");
      epochsToReturn.add(eveningEpoch);
      epochsToReturn.add(morningEpoch);
      return epochsToReturn;
    }
  }

  public long[] getEpochsPlusTwoHoursToEach(long[] epochs) {
    List<Long> epochsToReturn = new ArrayList<>();
    for (long epoch : epochs) {
      long epochPlus1hr = epoch + ONE_HOUR_EPOCH;
      long epochPlus2hr = epochPlus1hr + ONE_HOUR_EPOCH;
      epochsToReturn.add(epoch);
      epochsToReturn.add(epochPlus1hr);
      epochsToReturn.add(epochPlus2hr);
    }

    return convertListToArray(epochsToReturn);
  }

  public long[] getEpochPlusOneAndTwoHours(long epoch) {
    long[] longArray = new long[3];
    long epochPlus1hr = epoch + ONE_HOUR_EPOCH;
    long epochPlus2hr = epochPlus1hr + ONE_HOUR_EPOCH;

    //    longArray[0] = epoch;
    longArray[1] = epochPlus1hr;
    longArray[2] = epochPlus2hr;

    return longArray;
  }

  public String getDatetimeFromEpochSeconds(Long epoch) {
    Instant instant = Instant.ofEpochSecond(epoch);
    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    return dateTime.format(dtf);
  }

  public String getDateTimeAsDdMmYyyyFromWebsite(String websiteDate) {
    try {
      LocalDate localDate =
          LocalDate.parse(websiteDate, DateTimeFormatter.ofPattern("dd MMM yyyy"));

      return localDate.format(dtfMinusHours);
    } catch (DateTimeParseException e) {
      throw e;
    }
  }

  private long[] convertListToArray(List<Long> inputs) {
    long[] longArray = new long[inputs.size()];

    for (int i = 0; i < inputs.size(); i++) {
      longArray[i] = inputs.get(i);
    }

    return longArray;
  }
}
