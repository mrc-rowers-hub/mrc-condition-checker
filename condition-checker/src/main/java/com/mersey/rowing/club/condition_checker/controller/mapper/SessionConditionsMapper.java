package com.mersey.rowing.club.condition_checker.controller.mapper;

import com.mersey.rowing.club.condition_checker.controller.boats.BoatCapabilityClient;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.response.BoatsAllowed;
import com.mersey.rowing.club.condition_checker.model.response.SessionConditions;
import com.mersey.rowing.club.condition_checker.model.response.WeatherConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SessionConditionsMapper {

    @Autowired
    BoatCapabilityClient boatCapabilityClient;

    private static DateTimeFormatter TEMP_dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public SessionConditions mapSessionConditionsFromOpenWeatherResponse(OpenWeatherResponse openWeatherResponse, String dateTime){ // hmmm, how are we going to get the time here... probably needs passing in from the initial call too?
        // DON'T test certain dateTime scenarios yet, as this will change on Sam's ticket
        BoatsAllowed boatsAllowed = boatCapabilityClient.getBoatsAllowed(openWeatherResponse);

        WeatherConditions weatherConditions = WeatherConditions.builder().description(openWeatherResponse.getDescription()).windSpeed((int) openWeatherResponse.getWindSpeed()).tempFeelsLike((int) openWeatherResponse.getFeelsLike()).build();

        LocalDateTime TEMP_dateTime1 = LocalDateTime.parse(dateTime, TEMP_dtf);

        return SessionConditions.builder().weatherConditions(weatherConditions).boatsAllowed(boatsAllowed).date(TEMP_dateTime1).build();
        // Todo, wait for Sam's ticket, and use the dateTimeUtil to grab the date - or do a little bit more refactoring
    }
}
