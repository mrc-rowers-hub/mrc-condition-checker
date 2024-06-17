package com.mersey.rowing.club.condition_checker;

import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConditionCheckerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConditionCheckerApplication.class, args);
	}
}
