package com.mersey.rowing.club.condition_checker.controller.openweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

public class OpenWeatherApiClient {
//    @Autowired
//    private RestTemplate restTemplate;

    //Just a placeholder until I get spring sorted
    RestTemplate restTemplate = new RestTemplate();


    @Value("${open-weather-api.key}")
    private String apiKey;

    @Value("${open-weather-api.url}")
    private String apiUrl;

    //Lat and long for Rowing Club
    private final String clubLatitude = "53.39289";
    private final String clubLongitude = "-2.98536";

    public void callOpenWeatherApi() {

        //Formatting API url for Liverpool Mersey Rowing Club and adding API key
//        String url = String.format(apiUrl, clubLatitude, clubLongitude, apiKey);

        String weatherResponse = restTemplate.getForObject("https://catfact.ninja/fact?max_length=140\n", String.class);
        System.out.println(weatherResponse);

        //"https://api.openweathermap.org/data/3.0/onecall?lat=53.39293&lon=-2.98532&exclude=&appid=30e37ff4eced9848ff7533eb14b0c685"
    }
}
