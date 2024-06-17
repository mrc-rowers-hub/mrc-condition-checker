package com.mersey.rowing.club.condition_checker.model.response;

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
    private WeatherConditions weatherConditions;
    private BoatsAllowed boatsAllowed;

}
