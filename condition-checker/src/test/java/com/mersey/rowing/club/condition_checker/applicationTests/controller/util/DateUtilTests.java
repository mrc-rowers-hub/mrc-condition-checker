package com.mersey.rowing.club.condition_checker.applicationTests.controller.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DateUtilTests {

  private long testEpoch = 1721562600L;
  private long testEpochMorning = 1721538000L;
  private String testDate = "21/07/2024";
  private String testTime = "12:50";

  @Autowired
  private DateUtil dateUtil;

  @Test
  void getEpochsDateAndTimeSupplied_validDateTimeSupplied_returnsEpochTimeAsLong() {
    long actual = dateUtil.getEpochsDateAndTimeSupplied(testDate, testTime)[0];
    assertEquals(testEpoch, actual);
  }

  @Test
  void getEpochsTimeOnlyIsNull_validDateSupplied_returnsLongListOfTwoElements() {
    int actualLength = dateUtil.getEpochsTimeOnlyIsNull(testDate).length;
    int expectedLength = 2;
    assertEquals(expectedLength, actualLength);
  }

  @Test
  void getEpochsTimeOnlyIsNull_validDateSupplied_returnsEpochTimeAsLong() {
    long[] actualMorningEpoch = (dateUtil.getEpochsTimeOnlyIsNull(testDate));
    assertEquals(testEpochMorning, actualMorningEpoch[0]);
  }

  @Test
  void getEpochTimeAsLong_validDateTimeSupplied_returnsEpochTimeAsLong() {
    long actual = dateUtil.getEpochTimeAsLong(testDate, testTime);
    assertEquals(testEpoch, actual);
  }

  @Test
  void getEpochTimeAsLong_invalidDateTimeSupplied_doesNotReturnEpochTime() {
    Assertions.assertThrows(
        DateTimeParseException.class, () -> dateUtil.getEpochTimeAsLong(testDate, "245"));
  }

  @Test
  void getEpochsDateNullAndTimeNull_afterSixMorningBeforeSixEvening_returnsExpected() {
    // Todo needs mocking
  }

  @Test
  void getDatetimeFromEpoch_epochInput_returnsDtInExpectedFormat() {
    assertEquals(testDate + " " + testTime, dateUtil.getDatetimeFromEpochSeconds(1721562625L));
  }
}
