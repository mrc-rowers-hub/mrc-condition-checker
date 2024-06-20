package com.mersey.rowing.club.condition_checker.controller;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@Slf4j
public class ConditionController {

    // will return a response of weather summary, and boats that can go out
    // call open weather API and map the response
    // pass this into the BoatCapabilityClient to assess which boats can go

    // Example call-ish for memory
    // long callApiMorningInEpoch = LocalDateTime.of(dateToday, sixMorning).atZone(zoneId).toInstant().toEpochMilli();

    // Variables for the current date, current time, tomorrow's date, 6AM, and 6PM
    private final LocalDate dateToday = LocalDate.now();
    private final LocalDate dateTomorrow = LocalDate.now().plusDays(1);
    private final LocalTime sixMorning = LocalTime.of(6, 0);
    private final LocalTime sixEvening = LocalTime.of(18,0);
    private final LocalTime currentTime = LocalTime.now();
    private static final ZoneId zoneId = ZoneId.of("Europe/London");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    @GetMapping("/get_conditions")
    public void getConditions(@RequestHeader(value = "date", required = false) String date, @RequestHeader(value = "time", required = false) String time){
        // date = dd/MM/yyyy, // time = HH:mm

        // Logic to decide what calls are made to the API
        // Based on String date and String time entered by user

        // before calling DateUtil, should have validation of user input

        if(date == null && time == null || Objects.equals(date, dateToday.toString())) { // will this hit if date = today, and time specified? can't remember the logic req here
            DateUtil.callApiDateNullAndTimeNull(currentTime, sixMorning, sixEvening);
        } else if (date == null) {
            DateUtil.callApiDateOnlyIsNull(time);
        } else if (time == null) {
            DateUtil.callApiTimeOnlyIsNull(date);
        }else {
            DateUtil.callApiDateAndTimeSupplied(date, time);
        }
    }

}
