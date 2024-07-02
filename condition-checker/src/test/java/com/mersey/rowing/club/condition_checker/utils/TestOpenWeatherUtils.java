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
public class TestOpenWeatherUtils {

    public static final String BASE_URL = "http://localhost:5050";
    public static final String DUMMY_API_KEY = "testApiKey";
    public static final String TEST_EPOCH_TIME = "1625088000";

    public static String getOpenWeatherResponseAsString(){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try{
            return ow.writeValueAsString(getOpenWeatherResponse());
        } catch(JsonProcessingException e){
            log.error("Error while parsing JSON", e);
            throw new RuntimeException(e);
        }
    }

    public static OpenWeatherResponse getOpenWeatherResponse(){
        List<Weather> weatherList = List.of(
                new Weather(800, "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(279.13, 276.44, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }
}