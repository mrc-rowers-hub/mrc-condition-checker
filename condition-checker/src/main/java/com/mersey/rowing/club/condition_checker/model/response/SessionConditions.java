package com.mersey.rowing.club.condition_checker.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class SessionConditions {

  private String date;

  @JsonProperty("weather_conditions")
  private WeatherConditions weatherConditions;

  @JsonProperty("boats_allowed")
  private BoatsAllowed boatsAllowed;
}
