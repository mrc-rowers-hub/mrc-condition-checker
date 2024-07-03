package com.mersey.rowing.club.condition_checker.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("currentDateTime", WebsiteDateUtils.getDateNowFormatted());
        model.addAttribute("previousDays", WebsiteDateUtils.getPrevious7Days());
        model.addAttribute("nextDays", WebsiteDateUtils.getNext4Days());

        return "index";
    }
}