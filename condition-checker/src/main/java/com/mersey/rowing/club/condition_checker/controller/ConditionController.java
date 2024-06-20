package com.mersey.rowing.club.condition_checker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;

@RestController
@Slf4j
public class ConditionController {

    // will return a response of weather summary, and boats that can go out
    // call open weather API and map the response
    // pass this into the BoatCapabilityClient to assess which boats can go

    @GetMapping("/get_conditions")
    public void getConditions(@RequestHeader(value = "date", required = false) String date, @RequestHeader(value = "time", required = false) String time){

        // Variables for the current date, current time, tomorrow's date, 6AM, and 6PM
        LocalDate dateToday = LocalDate.now();
        LocalDate dateTomorrow = LocalDate.now().plusDays(1);
        LocalTime sixMorning = LocalTime.of(6, 0);
        LocalTime sixEvening = LocalTime.of(18,0);
        LocalTime currentTime = LocalTime.now();
        ZoneId zoneId = ZoneId.of("Europe/London");

        // Logic to decide what calls are made to the API
        // Based on String date and String time entered by user
        if(date == null && time == null || Objects.equals(date, dateToday.toString())) {
            callApiDateNullAndTimeNull(currentTime, sixMorning, sixEvening);
        } else if (date == null) {
            callApiDateOnlyIsNull();
        } else if (time == null) {
            callApiTimeOnlyIsNull(date, dateToday);
        }else {
            callApiDateAndTimeSupplied();
        }
    }

    private static void callApiDateAndTimeSupplied() {
        log.info("both date and time supplied");
        // Call API with (date, time)
    }

    private static void callApiTimeOnlyIsNull(String date, LocalDate dateToday) {
        log.info("time only is null");
        if (date.equals(dateToday.toString())) {
            // Run code under first section - maybe change to a switch statement?
        } else {
            // Call API with (date, sixMorning)
            // Call API with (date, sixEvening)
        }
    }

    private static void callApiDateOnlyIsNull() {
        log.info("date only is null");
        // Call API with (dateToday, time)
    }

    private static void callApiDateNullAndTimeNull(LocalTime currentTime, LocalTime sixMorning, LocalTime sixEvening) {
        log.info("date and time is null");
        // What the dt call should look like kinda (minus the API)
//            long callApiMorningInEpoch = LocalDateTime.of(dateToday, sixMorning).atZone(zoneId).toInstant().toEpochMilli();
//            long callApiEveningInEpoch = LocalDateTime.of(dateToday, sixEvening).atZone(zoneId).toInstant().toEpochMilli();
        // Check to see if current time is after 6AM
        if (currentTime.isAfter(sixMorning) && currentTime.isBefore(sixEvening)) {
            log.info("current time is: " + currentTime);
            // Call API with (dateToday, sixEvening) - callApiEveningInEpoch
            // Call API with (dateTomorrow, sixMorning)
        } else if (currentTime.isBefore(sixMorning)) {
            // Call API with (dateToday, sixMorning)
            // Call API with (dateToday, sixEvening)
        } else {
            // Call API with (dateTomorrow, sixMorning
            // Call API with (dateTomorrow, sixEvening
        }
    }
}
