package com.mersey.rowing.club.condition_checker.controller;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.time.*;

@RestController
@Slf4j
public class ConditionController {

    private final LocalDate dateToday = LocalDate.now();

    @GetMapping("/get_conditions")
    public void getConditions(@RequestHeader(value = "date", required = false) String date, @RequestHeader(value = "time", required = false) String time){
        // date = dd/MM/yyyy, // time = HH:mm


        // TODO before calling DateUtil, should have validation of user input

        getEpochBasedOnLogic(date, time);
    }

    private void getEpochBasedOnLogic(String date, String time) {
        if((date == null && time == null) || (dateToday.toString().equals(date) && time == null)) {
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
