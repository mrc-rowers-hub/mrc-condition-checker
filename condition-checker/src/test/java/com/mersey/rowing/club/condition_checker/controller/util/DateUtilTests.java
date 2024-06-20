package com.mersey.rowing.club.condition_checker.controller.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

public class DateUtilTests {

    // methodName_conditions_expectedResult
    @Test
    void callApiDateAndTimeSupplied_validDateTimeSupplied_returnsEpochTime() {
        long actual = DateUtil.callApiDateAndTimeSupplied("18/08/1999", "19:23");
        long expected = 935000580000L;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void callApiDateAndTimeSupplied_invalidDateTimeSupplied_doesntReturnEpochTime() {
        Assertions.assertThrows(DateTimeParseException.class, () -> DateUtil.callApiDateAndTimeSupplied("1/08/1999", "19:23"));
    }
}
