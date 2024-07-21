package com.mersey.rowing.club.condition_checker.applicationTests.controller.util;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTests {

    private long testEpoch = 1721562600000L;
    private long testEpochMorning = 1721538000000L;
    private String testDate = "21/07/2024";
    private String testTime = "12:50";

    @Test
    void getEpochsDateAndTimeSupplied_validDateTimeSupplied_returnsEpochTimeAsLong() {
        long actual = DateUtil.getEpochsDateAndTimeSupplied(testDate, testTime)[0];
//    long expected = 935000580000L;
        assertEquals(testEpoch, actual);
    }

    @Test
    void getEpochsTimeOnlyIsNull_validDateSupplied_returnsLongListOfTwoElements() {
        int actualLength = DateUtil.getEpochsTimeOnlyIsNull(testDate).length;
        int expectedLength = 2;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    void getEpochsTimeOnlyIsNull_validDateSupplied_returnsEpochTimeAsLong() {
        long[] actualMorningEpoch = (DateUtil.getEpochsTimeOnlyIsNull(testDate));
        assertEquals(testEpochMorning, actualMorningEpoch[0]);
    }

    @Test
    void getEpochTimeAsLong_validDateTimeSupplied_returnsEpochTimeAsLong() {
        long actual = DateUtil.getEpochTimeAsLong(testDate, testTime);
        assertEquals(testEpoch, actual);
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
    void getDatetimeFromEpoch_epochInput_returnsDtInExpectedFormat() {
        DateUtil dateUtil = new DateUtil();
        assertEquals(testDate + " " + testTime, dateUtil.getDatetimeFromEpochSeconds(1721562625L));
    }
}
