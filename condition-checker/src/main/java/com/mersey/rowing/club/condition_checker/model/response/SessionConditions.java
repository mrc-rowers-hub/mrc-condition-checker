package com.mersey.rowing.club.condition_checker.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class SessionConditions {

    private LocalDateTime date;

    @JsonProperty("weather")
    private WeatherConditions weatherConditions;

    @JsonProperty("boats_allowed")
    private BoatsAllowed boatsAllowed;

}
