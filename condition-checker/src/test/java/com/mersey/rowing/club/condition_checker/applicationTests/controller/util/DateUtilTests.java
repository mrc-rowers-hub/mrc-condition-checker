package com.mersey.rowing.club.condition_checker.applicationTests.controller.util;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

public class DateUtilTests {

    @Test
    void getEpochsDateAndTimeSupplied_validDateTimeSupplied_returnsEpochTimeAsLong() {
        long actual = DateUtil.getEpochsDateAndTimeSupplied("18/08/1999", "19:23")[0];
        long expected = 935000580000L;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getEpochsTimeOnlyIsNull_validDateSupplied_returnsLongListOfTwoElements() {
        int actualLength = DateUtil.getEpochsTimeOnlyIsNull("18/08/1999").length;
        int expectedLength = 2;
        Assertions.assertEquals(expectedLength, actualLength);
    }

    @Test
    void getEpochsTimeOnlyIsNull_validDateSupplied_returnsEpochTimeAsLong() {
        long[] actualMorningEpoch = (DateUtil.getEpochsTimeOnlyIsNull("18/08/1999"));
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
        Assertions.assertThrows(DateTimeParseException.class, () -> DateUtil.getEpochTimeAsLong("18/08/1999", "245"));
    }

    @Test
    void getEpochsDateNullAndTimeNull_afterSixMorningBeforeSixEvening_returnsExpected() {
        // Todo needs mocking 
    }
}

