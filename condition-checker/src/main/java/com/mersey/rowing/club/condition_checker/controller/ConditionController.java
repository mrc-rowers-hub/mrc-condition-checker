package com.mersey.rowing.club.condition_checker.controller;

import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@RestController
@Slf4j
public class ConditionController {

    @GetMapping("/get_conditions")
    public void getConditions(@RequestHeader(value = "date", required = false) String date, @RequestHeader(value = "time", required = false) String time){

        //Variables / fields?? for the current date, current time, tomorrow's date, 6AM, and 6PM
        LocalDate dateToday = LocalDate.now();
        LocalDate dateTomorrow = LocalDate.now().plusDays(1);
        LocalTime sixMorning = LocalTime.of(6, 0);
        LocalTime sixEvening = LocalTime.of(18,0);
        LocalTime currentTime = LocalTime.now();

        if(date == null && time == null){
            log.info("date and time is null");
            ZoneId zoneId = ZoneId.of("Europe/London");
            // What the dt call should look like (minus the API)
            long callApiMorningInEpoch = LocalDateTime.of(dateToday, sixMorning).atZone(zoneId).toInstant().toEpochMilli();
            long callApiEveningInEpoch = LocalDateTime.of(dateToday, sixEvening).atZone(zoneId).toInstant().toEpochMilli();
            // Check to see if current time is after 6AM
            if (currentTime.isAfter(sixMorning) && currentTime.isBefore(sixEvening)) {
                log.info("current time is: " + currentTime);
                // Call API with (dateToday, sixEvening) - callApiEveningInEpoch
                // Call API with (dateTomorrow, sixMorning)
                long callApiTomorrowMorningInEpoch = LocalDateTime.of(dateTomorrow, sixMorning).atZone(zoneId).toInstant().toEpochMilli();
            } else if (currentTime.isBefore(sixMorning)) {
                // Call API with (dateToday, sixMorning)
                // Call API with (dateToday, sixEvening)
            } else {
                // Call API with (dateTomorrow, sixMorning
                // Call API with (dateTomorrow, sixEvening
            }
        } else if (date == null) {
            log.info("date only is null");
            // Call API with (dateToday, timeSupplied)
        } else if (time == null) {
            log.info("time only is null");
            // Need to change this to be same type of object? Not sure
            if (date.equals(dateToday)) {
                // Run code under first section - maybe change to a switch statement?
            } else {
                // Call API with (dateSupplied, sixMorning)
                // Call API with (dateSupplied, sixEvening)
            }
            // will return a response of weather summary, and boats that can go out
            // call open weather API and map the response
            // pass this into the BoatCapabilityClient to assess which boats can go
        }else{
            log.info("both date and time supplied");
            //specific
        }
    }
}
