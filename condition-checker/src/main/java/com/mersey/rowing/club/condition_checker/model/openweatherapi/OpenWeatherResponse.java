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
public class OpenWeatherResponse {

    @JsonProperty("data")
    private List<WeatherData> data;

}
