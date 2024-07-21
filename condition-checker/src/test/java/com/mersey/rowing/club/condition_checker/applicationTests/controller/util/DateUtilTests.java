package com.mersey.rowing.club.condition_checker.applicationTests.controller.util;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateUtilTests {

  private long expectedEpoch = 1721562600000L;
  private long expectedMorningEpoch = 1721538000000L;
  private String testDate = "21/07/2024";
  private String expectedTime = "12:50";

  @Test
  void getEpochsDateAndTimeSupplied_validDateTimeSupplied_returnsEpochTimeAsLong() {
    long actual = DateUtil.getEpochsDateAndTimeSupplied(testDate, expectedTime)[0];
//    long expected = 935000580000L;
    Assertions.assertEquals(expectedEpoch, actual);
  }

  @Test
  void getEpochsTimeOnlyIsNull_validDateSupplied_returnsLongListOfTwoElements() {
    int actualLength = DateUtil.getEpochsTimeOnlyIsNull(testDate).length;
    int expectedLength = 2;
    Assertions.assertEquals(expectedLength, actualLength);
  }

  @Test
  void getEpochsTimeOnlyIsNull_validDateSupplied_returnsEpochTimeAsLong() {
    long[] actualMorningEpoch = (DateUtil.getEpochsTimeOnlyIsNull(testDate));
    Assertions.assertEquals(expectedMorningEpoch, actualMorningEpoch[0]);
  }

  @Test
  void getEpochTimeAsLong_validDateTimeSupplied_returnsEpochTimeAsLong() {
    long actual = DateUtil.getEpochTimeAsLong(testDate, expectedTime);
    Assertions.assertEquals(expectedEpoch, actual);
  }

  @Test
  void getEpochTimeAsLong_invalidDateTimeSupplied_doesNotReturnEpochTime() {
    Assertions.assertThrows(
        DateTimeParseException.class, () -> DateUtil.getEpochTimeAsLong(testDate, "245"));
  }

  @Test
  void getEpochsDateNullAndTimeNull_afterSixMorningBeforeSixEvening_returnsExpected() {
    // Todo needs mocking
  }

  @Test
  void getDatetimeFromEpoch_epochInput_returnsDtInExpectedFormat(){
    DateUtil.getDatetimeFromEpoch(935000580000L);
  }
}
