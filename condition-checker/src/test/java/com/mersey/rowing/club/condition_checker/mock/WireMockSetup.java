package com.mersey.rowing.club.condition_checker.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.Weather;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.WeatherData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class WireMockSetup {

    private final String endpoint = "/timemachine";

    public MappingBuilder setupOpenWeatherMapping() {
        String body = getOpenWeatherResponseAsString();

        return get(urlPathMatching(endpoint)).willReturn(aResponse().withStatus(200).withBody(body).withHeader("Content-Type", "application/json"));
    }

    public String getOpenWeatherResponseAsString(){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try{
            return ow.writeValueAsString(getOpenWeatherResponse());
        } catch(JsonProcessingException e){
            log.error("Error while parsing JSON", e);
            throw new RuntimeException(e);
        }
    }

    // move the below to testUtils
    public OpenWeatherResponse getOpenWeatherResponse(){
        List<Weather> weatherList = List.of(
                new Weather(800, "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(1718653615,279.13, 276.44, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }

}
