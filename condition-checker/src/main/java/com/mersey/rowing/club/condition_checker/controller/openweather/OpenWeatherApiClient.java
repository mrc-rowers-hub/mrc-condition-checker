package com.mersey.rowing.club.condition_checker.controller.openweather;

import org.springframework.beans.factory.annotation.Value;

public class OpenWeatherApiClient {

    @Value("${open-weather-api.key}")
    private String apiKey;
}
