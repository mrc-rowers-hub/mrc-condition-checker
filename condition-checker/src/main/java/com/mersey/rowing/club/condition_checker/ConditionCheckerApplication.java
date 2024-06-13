package com.mersey.rowing.club.condition_checker;

import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConditionCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConditionCheckerApplication.class, args);

		OpenWeatherApiClient owac = new OpenWeatherApiClient();

		owac.callOpenWeatherApi();
	}
}
