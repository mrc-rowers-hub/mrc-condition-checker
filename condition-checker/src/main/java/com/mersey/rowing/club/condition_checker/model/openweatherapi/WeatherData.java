package com.mersey.rowing.club.condition_checker.model.openweatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class WeatherData {

  @JsonProperty("dt")
  private int epochDateTime; // TODO hmmm is this okay - should be long?

  @JsonProperty("temp")
  private double temperature; // todo, do we use?

  @JsonProperty("feels_like")
  private double feelsLike; // fahrenheit

  @JsonProperty("wind_speed")
  private double windSpeed; // in miles per hour

  @JsonProperty("weather")
  private List<Weather> weather;

  public double feelsLikeFahrenheitToCelsius() {
    return (feelsLike - 32) * 5 / 9;
  }

  public double mphToKmph() {
    double CONVERSION = 1.60934;
    return windSpeed * CONVERSION;
  }
}
