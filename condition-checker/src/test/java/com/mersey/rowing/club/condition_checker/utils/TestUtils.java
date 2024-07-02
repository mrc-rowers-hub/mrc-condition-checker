package com.mersey.rowing.club.condition_checker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.Weather;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.WeatherData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TestUtils {

    public String getOpenWeatherResponseAsString(String dt){
        int dtToUse = (dt == null) ? 1718653615 : Integer.parseInt(dt);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try{
            return ow.writeValueAsString(getOpenWeatherResponse(dtToUse));
        } catch(JsonProcessingException e){
            log.error("Error while parsing JSON", e);
            throw new RuntimeException(e);
        }
    }

    public OpenWeatherResponse getOpenWeatherResponse(int dtToUse){
        List<Weather> weatherList = List.of(
                new Weather(800, "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(dtToUse,279.13, 276.44, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }
}
