package com.mersey.rowing.club.condition_checker.controller.openweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@Component
@Slf4j
public class OpenWeatherApiClient {
//    @Autowired
//    private RestTemplate restTemplate;

    //Just a placeholder until I get spring sorted
    RestTemplate restTemplate = new RestTemplate();


    @Value("${open-weather-api.key}")
    private String apiKey;

    @Value("${open-weather-api.baseUrl}")
    private String apiBaseUrl;

    @Value("${open-weather-api.endpoint}")
    private String apiEndpoint;

    private final String tempEndpoint = "https://api.openweathermap.org/data/3.0/onecall/timemachine?lat=53.39293&lon=-2.98532&dt=1718653615&appid=30e37ff4eced9848ff7533eb14b0c685";

    //Epoch time for date time in api url
    Instant instant = Instant.ofEpochMilli(1718653615L);

    public void callOpenWeatherApi() {

        //Formatting API url
//        String url = String.format(apiBaseUrl + apiEndpoint, instant.toString(), apiKey);
//        System.out.println(apiEndpoint);
        log.info(tempEndpoint);

//        WeatherResponse weatherResponse = restTemplate.getForObject();
//        System.out.println(weatherResponse);

        //Storing api for later
        //"https://api.openweathermap.org/data/3.0/onecall?lat=53.39293&lon=-2.98532&exclude=&appid=30e37ff4eced9848ff7533eb14b0c685"
    }
}
