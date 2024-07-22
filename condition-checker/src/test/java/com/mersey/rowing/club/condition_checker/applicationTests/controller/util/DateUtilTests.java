package com.mersey.rowing.club.condition_checker.applicationTests.controller.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class DateUtilTests {

  private long testEpoch = 1721562600L;
  private long testEpochMorning = 1721538000L;
  private String testDate = "21/07/2024";
  private String testTime = "12:50";

  @Autowired private DateUtil dateUtil;

  @MockBean private Clock clock;

  @BeforeEach
  void init() {
    // Ensure the clock returns UTC timezone
    when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
  }

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
  void getEpochsDateNullAndTimeNull_afterSixMorningBeforeSixEvening_returnsTonightAndTomorrowMorn() {
    Instant fixedInstant = Instant.parse("2024-07-21T12:50:00Z");
    when(clock.instant()).thenReturn(fixedInstant);

    long expectedEpoch1 = 1721581200L; // 2024-07-21 18:00:00 UTC
    long expectedEpoch2 = 1721624400L; // 2024-07-22 06:00 UTC
    long[] expected = {expectedEpoch1, expectedEpoch2};
    long[] result = dateUtil.getEpochsDateNullAndTimeNull();

    assertArrayEquals(expected, result);
  }

  @Test
  void getEpochsDateNullAndTimeNull_beforeSixMorning_returnsThisMorningAndTonight() {
    Instant fixedInstant = Instant.parse("2024-07-21T04:00:00Z");
    when(clock.instant()).thenReturn(fixedInstant);

    long expectedEpoch1 = 1721538000L; // 2024-07-21 06:00:00 UTC
    long expectedEpoch2 = 1721581200L; // 2024-07-21 18:00:00 UTC
    long[] expected = {expectedEpoch1, expectedEpoch2};
    long[] result = dateUtil.getEpochsDateNullAndTimeNull();

    assertArrayEquals(expected, result);
  }

  @Test
  void getEpochsDateNullAndTimeNull_AfterSixEvening_returnsTomorrowMorningAndEvening() {
    Instant fixedInstant = Instant.parse("2024-07-21T21:00:00Z");
    when(clock.instant()).thenReturn(fixedInstant);

    long expectedEpoch1 = 1721624400L; // 2024-07-22 06:00 UTC
    long expectedEpoch2 = 1721667600L; // 2024-07-22 18:00:00 UTC
    long[] expected = {expectedEpoch1, expectedEpoch2};
    long[] result = dateUtil.getEpochsDateNullAndTimeNull();

    assertArrayEquals(expected, result);
  }

  @Test
  void getDatetimeFromEpoch_epochInput_returnsDtInExpectedFormat() {
    // Mock the clock to ensure consistent timezone
    when(clock.getZone()).thenReturn(ZoneId.of("Europe/London"));

    assertEquals(testDate + " " + testTime, dateUtil.getDatetimeFromEpochSeconds(1721562625L));
  }

  @Test
  void getDateTimeAsDdMmYyyyFromWebsite_stringInput_returnsDtInExpectedFormat() {
    assertEquals("12/07/2024", dateUtil.getDateTimeAsDdMmYyyyFromWebsite("12 Jul 2024"));
  }
}
