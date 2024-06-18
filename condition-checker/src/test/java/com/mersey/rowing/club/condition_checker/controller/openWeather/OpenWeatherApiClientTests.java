package com.mersey.rowing.club.condition_checker.controller.openWeather;


import com.mersey.rowing.club.condition_checker.WiremockBaseTests;
import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "open-weather-api.baseUrl=http://localhost:5050" })
public class OpenWeatherApiClientTests extends WiremockBaseTests {

    @Autowired
    private OpenWeatherApiClient openWeatherApiClient;

    @Test
    void randomTest(){
        openWeatherApiClient.getOpenWeatherAPIResponse();
    }

    // want to call Open... getOpenWeatherAPIResponse()
    // but want it to use wiremock, not the actual api
    // so, need to change the baseUrl
    // can either have .env
    // or figure out how to change in the tests
}
