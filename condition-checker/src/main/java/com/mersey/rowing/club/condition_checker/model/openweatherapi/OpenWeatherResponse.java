package com.mersey.rowing.club.condition_checker.model.openweatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class OpenWeatherResponse {

  @JsonProperty("data")
  private List<WeatherData> data;

  public String getDescription() {
    return data.getFirst().getWeather().getFirst().getDescription();
  }

  public double getWindSpeed() {
    return data.getFirst().mphToKmph();
  }

  public double getFeelsLike() {
    return data.getFirst().kelvinToCelsius();
  }
}
