package com.mersey.rowing.club.condition_checker.controller;

import com.mersey.rowing.club.condition_checker.controller.response.ConditionResponseClient;
import com.mersey.rowing.club.condition_checker.model.response.ConditionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConditionController {

  @Autowired ConditionResponseClient conditionResponseClient;

  @GetMapping("/get_conditions")
  public ResponseEntity<ConditionResponse> getConditions(
      @RequestHeader(value = "date", required = false) String date,
      @RequestHeader(value = "time", required = false) String time) {
    // date = dd/MM/yyyy, // time = HH:mm
    // TODO before calling DateUtil, should have validation of user input

    return conditionResponseClient.getConditionResponseFromDateTime(date, time);
  }
}
