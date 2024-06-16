package com.mersey.rowing.club.condition_checker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class ConditionController {

    // Date in format YYYY-MM-dd, time in format HH:mm
    @GetMapping("/get_conditions")
    public void getConditions(@RequestParam(value = "date", required = false) String date, @RequestParam(value = "time", required = false) String time){
        log.info("date supplied {}, time supplied {}", date, time);
    }

    private LocalDateTime getDateTimeToSearch(String date, String time){
        LocalDateTime suppliedLocalDateTime = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if(date == null && time == null){
            log.info("date and time is null, default to now");
            return suppliedLocalDateTime;
        } else if (date == null) {
            log.info("date only is null, default to today");
            LocalTime localTime = LocalTime.parse(time, timeFormatter);
            return LocalDateTime.of(suppliedLocalDateTime.toLocalDate(), localTime);
        } else if (time == null) {
            log.info("time only is null, default to 6am"); // consider if this is done for today,... only do 6am if in the future, otherwise do now perhaps?
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            time = "06:00";
            LocalTime localTime = LocalTime.parse(time, timeFormatter);
            return LocalDateTime.of(localDate, localTime);
        }else{
            log.info("both date and time supplied");
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            LocalTime localTime = LocalTime.parse(time, timeFormatter);
            return LocalDateTime.of(localDate, localTime);
        }
    }

}
