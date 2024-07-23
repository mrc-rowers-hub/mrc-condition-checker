package com.mersey.rowing.club.condition_checker.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.sql.Time;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class SessionConditions {

  @JsonProperty("time_during_session")
  private TimeType timeType;

  @JsonProperty("session_uuid")
  private String sessionUUID;

  private String status;

  private String date;

  @JsonProperty("weather_conditions")
  private WeatherConditions weatherConditions;

  @JsonProperty("boats_allowed")
  private BoatsAllowed boatsAllowed;
}
