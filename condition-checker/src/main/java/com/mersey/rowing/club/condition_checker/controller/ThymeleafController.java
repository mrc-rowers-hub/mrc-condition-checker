package com.mersey.rowing.club.condition_checker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class ThymeleafController {

    @GetMapping("/")
    public String index(Model model) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/London"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        model.addAttribute("currentDateTime", formattedDateTime);
        return "index";
    }
}