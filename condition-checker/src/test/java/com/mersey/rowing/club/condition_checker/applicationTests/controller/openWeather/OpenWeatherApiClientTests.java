package com.mersey.rowing.club.condition_checker.applicationTests.controller.openWeather;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mersey.rowing.club.condition_checker.applicationTests.WiremockBaseTests;
import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OpenWeatherApiClientTests extends WiremockBaseTests {

  @Autowired private OpenWeatherApiClient openWeatherApiClient;

  @Test
  void getOpenWeatherAPIResponse_apiGivesExpectedResponse_mapsToOpenWeatherResponseModel() {
    OpenWeatherResponse actualMappedResponse = openWeatherApiClient.getOpenWeatherAPIResponse();
    assertEquals(expectedGenericOpenWeatherResponse, actualMappedResponse);
  }

  // Todo API giving 500 doesn't map, should return null perhaps?

}
