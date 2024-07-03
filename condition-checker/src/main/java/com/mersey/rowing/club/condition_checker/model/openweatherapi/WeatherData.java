package com.mersey.rowing.club.condition_checker.model.openweatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class WeatherData {

    @JsonProperty("dt")
    private int epochDateTime;

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
}
