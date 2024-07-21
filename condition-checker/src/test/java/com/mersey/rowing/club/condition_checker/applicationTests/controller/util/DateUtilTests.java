package com.mersey.rowing.club.condition_checker.applicationTests.controller.util;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateUtilTests {

  @Test
  void callApiDateAndTimeSupplied_validDateTimeSupplied_returnsEpochTimeAsLong() {
    long actual = DateUtil.callApiDateAndTimeSupplied("18/08/1999", "19:23");
    long expected = 935000580000L;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void callApiTimeOnlyIsNull_validDateSupplied_returnsLongListOfTwoElements() {
    int actualLength = DateUtil.callApiTimeOnlyIsNull("18/08/1999").length;
    int expectedLength = 2;
    Assertions.assertEquals(expectedLength, actualLength);
  }

  @Test
  void callApiTimeOnlyIsNull_validDateSupplied_returnsEpochTimeAsLong() {
    long[] actualMorningEpoch = (DateUtil.callApiTimeOnlyIsNull("18/08/1999"));
    long expectedMorningEpoch = 934952400000L;
    Assertions.assertEquals(actualMorningEpoch[0], expectedMorningEpoch);
  }

  @Test
  void getEpochTimeAsLong_validDateTimeSupplied_returnsEpochTimeAsLong() {
    long actual = DateUtil.getEpochTimeAsLong("18/08/1999", "19:23");
    long expected = 935000580000L;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void getEpochTimeAsLong_invalidDateTimeSupplied_doesNotReturnEpochTime() {
    Assertions.assertThrows(
        DateTimeParseException.class, () -> DateUtil.getEpochTimeAsLong("18/08/1999", "245"));
  }

  @Test
  void callApiDateNullAndTimeNull_afterSixMorningBeforeSixEvening_returnsExpected() {
    // Todo needs mocking
  }
}
