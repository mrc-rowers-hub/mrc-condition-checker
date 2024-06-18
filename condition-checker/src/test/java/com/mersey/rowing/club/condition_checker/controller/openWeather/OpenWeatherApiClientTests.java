package com.mersey.rowing.club.condition_checker.controller.openWeather;


import com.mersey.rowing.club.condition_checker.WiremockBaseTests;
import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenWeatherApiClientTests extends WiremockBaseTests {

    @Autowired
    private OpenWeatherApiClient openWeatherApiClient;

    @Test
    void getOpenWeatherAPIResponse_apiGivesExpectedResponse_mapsToOpenWeatherResponseModel(){
        OpenWeatherResponse actualMappedResponse = openWeatherApiClient.getOpenWeatherAPIResponse();
        assertEquals(expectedOpenWeatherResponse, actualMappedResponse);
    }

    // Todo API giving 500 doesn't map, should return null perhaps?

}
