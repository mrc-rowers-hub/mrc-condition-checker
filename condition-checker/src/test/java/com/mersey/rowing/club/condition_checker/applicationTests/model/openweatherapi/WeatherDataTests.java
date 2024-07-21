package com.mersey.rowing.club.condition_checker.applicationTests.model.openweatherapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mersey.rowing.club.condition_checker.model.openweatherapi.WeatherData;
import org.junit.jupiter.api.Test;

public class WeatherDataTests {

  @Test
  void weatherData_builtWithoutTempInCelsius_setsTempInCelsius() {
    WeatherData myWeatherData = WeatherData.builder().temperature(280).build();
    assertEquals(7, myWeatherData.kelvinToCelsius());
  }
}
