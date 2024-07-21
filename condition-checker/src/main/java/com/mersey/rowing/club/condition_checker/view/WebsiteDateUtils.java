package com.mersey.rowing.club.condition_checker.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsiteDateUtils {

  static LocalDate currentDate = LocalDate.now();
  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

  public static String getDateNowFormatted() {
    return currentDate.format(formatter);
  }

  public static List<String> getPrevious7Days() {
    List<String> previousDays = new ArrayList<>();
    for (int i = 1; i <= 7; i++) {
      LocalDate previousDate = currentDate.minusDays(i);
      previousDays.add(previousDate.format(formatter));
    }

    return previousDays;
  }

  public static List<String> getNext4DaysAndToday() {
    List<String> nextDays = new ArrayList<>();

    nextDays.add(currentDate.format(formatter));

    for (int i = 1; i <= 4; i++) {
      LocalDate nextDate = currentDate.plusDays(i);
      nextDays.add(nextDate.format(formatter));
    }

    return nextDays;
  }
}
