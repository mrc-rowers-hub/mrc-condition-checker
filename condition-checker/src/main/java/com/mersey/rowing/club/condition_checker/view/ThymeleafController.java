package com.mersey.rowing.club.condition_checker.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mersey.rowing.club.condition_checker.controller.response.ConditionResponseClient;
import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
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

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    String infoToDisplay;
    try {
      infoToDisplay =
          ow.writeValueAsString(
              conditionResponseClient.getConditionResponseFromDateTime(dateTime, null));
    } catch (JsonProcessingException e) {
      log.error("Error while parsing JSON", e);
      throw new RuntimeException(e);
    }

    log.info(infoToDisplay);

    model.addAttribute("infoToDisplay", infoToDisplay);

    return "dateDetails";
  }
}
