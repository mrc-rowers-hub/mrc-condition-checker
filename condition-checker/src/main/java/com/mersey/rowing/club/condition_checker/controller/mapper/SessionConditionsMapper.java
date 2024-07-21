package com.mersey.rowing.club.condition_checker.controller.mapper;

import com.mersey.rowing.club.condition_checker.controller.boats.BoatCapabilityClient;
import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.response.BoatsAllowed;
import com.mersey.rowing.club.condition_checker.model.response.SessionConditions;
import com.mersey.rowing.club.condition_checker.model.response.WeatherConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionConditionsMapper {

  @Autowired BoatCapabilityClient boatCapabilityClient;

  private DateUtil dateUtil = new DateUtil();

  public SessionConditions mapFromOwResponse(OpenWeatherResponse openWeatherResponse) {
    BoatsAllowed boatsAllowed = boatCapabilityClient.getBoatsAllowed(openWeatherResponse);

    WeatherConditions weatherConditions =
        WeatherConditions.builder()
            .description(openWeatherResponse.getDescription())
            .windSpeed((int) Math.round(openWeatherResponse.getWindSpeed()))
            .tempFeelsLike((int) Math.round(openWeatherResponse.getFeelsLike()))
            .build();

    String dateTime =
        dateUtil.getDatetimeFromEpochSeconds(
            (long) openWeatherResponse.getData().getFirst().getEpochDateTime());

    return SessionConditions.builder()
        .weatherConditions(weatherConditions)
        .boatsAllowed(boatsAllowed)
        .date(dateTime)
        .build();
  }
}
