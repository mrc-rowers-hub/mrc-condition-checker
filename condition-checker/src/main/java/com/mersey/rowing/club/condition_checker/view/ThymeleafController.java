package com.mersey.rowing.club.condition_checker.view;

import com.mersey.rowing.club.condition_checker.controller.response.ConditionResponseClient;
import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import com.mersey.rowing.club.condition_checker.model.response.ConditionResponse;
import com.mersey.rowing.club.condition_checker.model.response.SessionConditions;
import com.mersey.rowing.club.condition_checker.model.response.TimeType;
import java.util.List;
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
    List<SessionConditions> conditionResponseOfStartTimes =
        conditionResponse.getSessionConditions().stream()
            .filter(
                sessionConditions -> sessionConditions.getTimeType().equals(TimeType.SESSION_START))
            .toList();

    for (SessionConditions sessionConditions : conditionResponseOfStartTimes) {
      String uuid = sessionConditions.getSessionUUID();
      List<SessionConditions> conditionsDuringSession =
          conditionResponse.getSessionConditions().stream()
              .filter(sessionConditions1 -> sessionConditions1.getSessionUUID().equals(uuid))
              .toList();
    }

    model.addAttribute("sessionConditions", conditionResponseOfStartTimes);

    return "dateDetails";
  }
}
