package com.mersey.rowing.club.condition_checker.mockSetup;

import static com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils.TEST_EPOCH_TIME;

import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.Weather;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.WeatherData;
import java.util.List;

public class MockOpenWeatherResponseGenerator {

  public static OpenWeatherResponse getOpenWeatherResponseAllGood() {
    List<Weather> weatherList = List.of(new Weather(800, "clear sky", "01d"));
    List<WeatherData> weatherDataList =
        List.of(new WeatherData(TEST_EPOCH_TIME, 279.13, 276.44, 2, weatherList));
    return OpenWeatherResponse.builder().data(weatherDataList).build();
  }

  public static OpenWeatherResponse getOpenWeatherResponseExemptId() {
    List<Weather> weatherList = List.of(new Weather(501, "clear sky", "01d"));
    List<WeatherData> weatherDataList =
        List.of(new WeatherData(TEST_EPOCH_TIME, 279.13, 276.44, 2, weatherList));
    return OpenWeatherResponse.builder().data(weatherDataList).build();
  }

  public static OpenWeatherResponse getOpenWeatherResponseUnacceptableIdSpecific() {
    List<Weather> weatherList = List.of(new Weather(302, "clear sky", "01d"));
    List<WeatherData> weatherDataList =
        List.of(new WeatherData(TEST_EPOCH_TIME, 279.13, 276.44, 2, weatherList));
    return OpenWeatherResponse.builder().data(weatherDataList).build();
  }

  public static OpenWeatherResponse getOpenWeatherResponseUnacceptableIdXx() {
    List<Weather> weatherList = List.of(new Weather(210, "clear sky", "01d"));
    List<WeatherData> weatherDataList =
        List.of(new WeatherData(TEST_EPOCH_TIME, 279.13, 276.44, 2, weatherList));
    return OpenWeatherResponse.builder().data(weatherDataList).build();
  }

  public static OpenWeatherResponse getOpenWeatherResponseWindSpeed(double windSpeed) {
    List<Weather> weatherList = List.of(new Weather(800, "clear sky", "01d"));
    List<WeatherData> weatherDataList =
        List.of(new WeatherData(TEST_EPOCH_TIME, 279.13, 276.44, getWindSpeedKmH(windSpeed), weatherList));
    return OpenWeatherResponse.builder().data(weatherDataList).build();
  }

  public static OpenWeatherResponse getOpenWeatherResponseFeelsLike(double feelsLike) {
    double feelsLikeKelvin = feelsLike + 273;
    List<Weather> weatherList = List.of(new Weather(800, "clear sky", "01d"));
    List<WeatherData> weatherDataList =
        List.of(new WeatherData(TEST_EPOCH_TIME, 279.13, feelsLikeKelvin, 2, weatherList));
    return OpenWeatherResponse.builder().data(weatherDataList).build();
  }

  private static double getWindSpeedKmH(double windspeedMPerS){
    return windspeedMPerS/3.6;
  }
}
