package com.mersey.rowing.club.condition_checker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {

    @GetMapping("/")
    public String index(Model model) {
        // Get current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        // List for holding dates
        List<String> previousDays = new ArrayList<>();
        List<String> nextDays = new ArrayList<>();

        // Generate previous 7 days
        for (int i = 1; i <= 7; i++) {
            LocalDate previousDate = currentDate.minusDays(i);
            previousDays.add(previousDate.format(formatter));
        }

        // Generate next 4 days
        for (int i = 1; i <= 4; i++) {
            LocalDate nextDate = currentDate.plusDays(i);
            nextDays.add(nextDate.format(formatter));
        }

        // Add attributes to model
        model.addAttribute("currentDateTime", currentDate.format(formatter));
        model.addAttribute("previousDays", previousDays);
        model.addAttribute("nextDays", nextDays);

        return "index";
    }
}