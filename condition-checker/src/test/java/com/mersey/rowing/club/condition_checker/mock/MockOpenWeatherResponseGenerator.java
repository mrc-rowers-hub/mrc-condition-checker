package com.mersey.rowing.club.condition_checker.mock;

import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.Weather;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.WeatherData;

import java.util.List;

public class MockOpenWeatherResponseGenerator {

    public OpenWeatherResponse getOpenWeatherResponseAllGood(){
        List<Weather> weatherList = List.of(
                new Weather(800, "Clear", "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(279.13, 276.44, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }

    public OpenWeatherResponse getOpenWeatherResponseExemptId(){
        List<Weather> weatherList = List.of(
                new Weather(501, "Clear", "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(279.13, 276.44, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }

    public OpenWeatherResponse getOpenWeatherResponseUnacceptableIdSpecific(){
        List<Weather> weatherList = List.of(
                new Weather(302, "Clear", "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(279.13, 276.44, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }

    public OpenWeatherResponse getOpenWeatherResponseUnacceptableIdXx(){
        List<Weather> weatherList = List.of(
                new Weather(210, "Clear", "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(279.13, 276.44, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }

    public OpenWeatherResponse getOpenWeatherResponseWindSpeed(double windSpeed){
        List<Weather> weatherList = List.of(
                new Weather(800, "Clear", "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(279.13, 276.44, windSpeed, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }

    public OpenWeatherResponse getOpenWeatherResponseGenericFeelsLike(double feelsLike){
        List<Weather> weatherList = List.of(
                new Weather(800, "Clear", "clear sky", "01d"));
        List<WeatherData> weatherDataList = List.of(new WeatherData(279.13, feelsLike, 3.6, weatherList));
        return OpenWeatherResponse.builder().data(weatherDataList).build();
    }

}
