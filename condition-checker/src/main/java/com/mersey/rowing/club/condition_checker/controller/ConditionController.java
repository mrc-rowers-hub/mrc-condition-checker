package com.mersey.rowing.club.condition_checker.controller;

import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.time.*;

@RestController
@Slf4j
public class ConditionController {

    private final LocalDate dateToday = LocalDate.now();
    @Autowired
    private OpenWeatherApiClient owac;

    @GetMapping("/get_conditions")
    public void getConditions(@RequestHeader(value = "date", required = false) String date, @RequestHeader(value = "time", required = false) String time){
        // date = dd/MM/yyyy, // time = HH:mm
        // TODO before calling DateUtil, should have validation of user input

        long[] epochs = getEpochBasedOnLogic(date, time);
        for (long epoch : epochs) {
            owac.getOpenWeatherAPIResponse(epoch);
        }

        // Call getOpenWeatherApiResponse in OpenWeatherApiClient class
    }

    private long[] getEpochBasedOnLogic(String date, String time) {
        if((date == null && time == null) || (dateToday.toString().equals(date) && time == null)) {
            return DateUtil.getEpochsDateNullAndTimeNull();
        } else if (date == null) {
            return DateUtil.getEpochsDateOnlyIsNull(time);
        } else if (time == null) {
            return DateUtil.getEpochsTimeOnlyIsNull(date);
        }else {
            return DateUtil.getEpochsDateAndTimeSupplied(date, time);
        }
    }
}
