package com.mersey.rowing.club.condition_checker.view;

import com.mersey.rowing.club.condition_checker.controller.boats.BoatCapabilityClient;
import com.mersey.rowing.club.condition_checker.controller.response.ConditionResponseClient;
import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import com.mersey.rowing.club.condition_checker.model.response.ConditionResponse;
import com.mersey.rowing.club.condition_checker.model.response.SessionConditions;
import java.util.List;

import com.mersey.rowing.club.condition_checker.model.response.TimeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ThymeleafController {

  @Autowired ConditionResponseClient conditionResponseClient;

  @Autowired DateUtil dateUtil;

  @Autowired
  BoatCapabilityClient boatCapabilityClient;

  @GetMapping("/")
  public String index(Model model) {

    model.addAttribute("currentDateTime", WebsiteDateUtils.getDateNowFormatted());
    model.addAttribute("previousDays", WebsiteDateUtils.getPrevious7Days());
    model.addAttribute("nextDays", WebsiteDateUtils.getNext4DaysAndToday());

    return "index";
  }

  @GetMapping("/date")
  public String dateDetails(@RequestParam("selectedDate") String selectedDate, Model model) {
    model.addAttribute("selectedDate", selectedDate);

    String dateTime = dateUtil.getDateTimeAsDdMmYyyyFromWebsite(selectedDate);

    ConditionResponse conditionResponse =
        conditionResponseClient.getConditionResponseFromDateTime(dateTime, null).getBody();
    // instead, want it to
    List<SessionConditions> conditionResponseOfStartTimes = conditionResponse.getSessionConditions().stream().filter(sessionConditions -> sessionConditions.getTimeType().equals(TimeType.SESSION_START)).toList();

    for(SessionConditions sessionConditions : conditionResponseOfStartTimes){
      // EACH SESSION - HAVE THIS IN A BIG BOX
      String uuid = sessionConditions.getSessionUUID();
      List<SessionConditions> conditionsDuringSession = conditionResponse.getSessionConditions().stream().filter(sessionConditions1 -> sessionConditions1.getSessionUUID().equals(uuid)).toList();
      // for the whole session, have one big 'boats permitted' thing:
      boatCapabilityClient.getSessionAverage(conditionsDuringSession); // I want this display in big - do not change this call
    } // want to map all of these like they are now, but in the same box - e.g. all sessions in one big box, then in their separate boxes

    // then remove the below
    model.addAttribute("sessionConditions", conditionResponseOfStartTimes);

    // and have in

    return "dateDetails";
  }
}
