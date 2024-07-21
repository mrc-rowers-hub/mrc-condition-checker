package com.mersey.rowing.club.condition_checker.model.response;

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

  private LocalDateTime date;
  private WeatherConditions weatherConditions;
  private BoatsAllowed boatsAllowed;
}
