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

    private final LocalDate dateToday = LocalDate.now();

    @GetMapping("/get_conditions")
    public void getConditions(@RequestHeader(value = "date", required = false) String date, @RequestHeader(value = "time", required = false) String time){
        // date = dd/MM/yyyy, // time = HH:mm

        // Logic to decide what calls are made to the API
        // Based on String date and String time entered by user

        // before calling DateUtil, should have validation of user input

        if((date == null && time == null) || (dateToday.toString().equals(date) && time == null)) { // will this hit if date = today, and time specified? can't remember the logic req here
            DateUtil.callApiDateNullAndTimeNull();
        } else if (date == null) {
            DateUtil.callApiDateOnlyIsNull(time);
        } else if (time == null) {
            DateUtil.callApiTimeOnlyIsNull(date);
        }else {
            DateUtil.callApiDateAndTimeSupplied(date, time);
        }
    }

}
