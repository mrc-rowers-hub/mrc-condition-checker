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
public class WeatherData {

  @JsonProperty("dt")
  private int epochDateTime; // TODO hmmm is this okay - should be long?

  @JsonProperty("temp")
  private double temperature;

  @JsonProperty("feels_like")
  private double feelsLike;

  @JsonProperty("wind_speed")
  private double windSpeed;

  @JsonProperty("weather")
  private List<Weather> weather;

  public double kelvinToCelsius() {
    return temperature - 273;
  }

  public double msTokmH() {
    return windSpeed * 3.6;
  }
}
