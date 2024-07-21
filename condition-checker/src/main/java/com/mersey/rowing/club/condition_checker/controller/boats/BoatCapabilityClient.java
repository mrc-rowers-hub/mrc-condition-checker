package com.mersey.rowing.club.condition_checker.controller.boats;

import com.mersey.rowing.club.condition_checker.model.boat.BoatLimits;
import com.mersey.rowing.club.condition_checker.model.boat.BoatType;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.Weather;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.WeatherData;
import com.mersey.rowing.club.condition_checker.model.response.BoatsAllowed;
import com.mersey.rowing.club.condition_checker.model.response.WeatherConditions;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BoatCapabilityClient {

  @Autowired BoatLimits boatLimits;

  public BoatsAllowed getBoatsAllowed(OpenWeatherResponse openWeatherResponse) {
    WeatherData weatherData = openWeatherResponse.getData().getFirst();
    int windSpeed = (int) Math.round(openWeatherResponse.getWindSpeed());
    Weather something = weatherData.getWeather().getFirst(); // rename
    // Todo, reassess the below, only accounting for one weather data response
    WeatherConditions weatherConditions =
        WeatherConditions.builder()
            .description(something.getDescription())
            .tempFeelsLike((int) Math.round(openWeatherResponse.getFeelsLike()))
            .windSpeed((int) Math.round(openWeatherResponse.getWindSpeed()))
            .build();

    if (!isTempWithinLimits(weatherData)) {
      return BoatsAllowed.builder()
          .doubles(false)
          .single(false)
          .noviceFourAndAbove(false)
          .seniorFourAndAbove(false)
          .build();
    } else if (!isIdWithinLimits(something)) {
      return BoatsAllowed.builder()
          .doubles(false)
          .single(false)
          .noviceFourAndAbove(false)
          .seniorFourAndAbove(false)
          .build();
    } else {
      return getBoatsAllowedByWind(windSpeed, boatLimits.getBoatTypeWindLimit());
    }
  }

  private BoatsAllowed getBoatsAllowedByWind(
      int actualWindSpeed, Map<BoatType, Integer> boatsAndLimits) {
    // Todo refactor this
    BoatsAllowed.BoatsAllowedBuilder boatsAllowedBuilder = BoatsAllowed.builder();

    if (actualWindSpeed <= boatsAndLimits.get(BoatType.SINGLE)) {
      return boatsAllowedBuilder
          .seniorFourAndAbove(true)
          .noviceFourAndAbove(true)
          .doubles(true)
          .single(true)
          .build();
    }

    if (actualWindSpeed > boatsAndLimits.get(BoatType.SENIOR_FOUR_AND_ABOVE)) {
      log.info("ALL BOATS CANCELLED: wind too high");
      return boatsAllowedBuilder.build();
    }
    boatsAllowedBuilder.seniorFourAndAbove(true);
    if (actualWindSpeed > boatsAndLimits.get(BoatType.NOVICE_FOUR_AND_ABOVE)) {
      log.info("SOME BOATS CANCELLED: wind too high");
      return boatsAllowedBuilder.build();
    }
    boatsAllowedBuilder.noviceFourAndAbove(true);
    if (actualWindSpeed > boatsAndLimits.get(BoatType.DOUBLE)) {
      log.info("SOME BOATS CANCELLED: wind too high");
      return boatsAllowedBuilder.build();
    }
    boatsAllowedBuilder.doubles(true);
    log.info("SOME BOATS CANCELLED: wind too high");
    return boatsAllowedBuilder.build();
  }

  private boolean isIdWithinLimits(Weather weather) {
    String id = String.valueOf(weather.getId());

    String[] unacceptableIds = boatLimits.getUnacceptableIds();
    String[] exceptionsToTheAbove = boatLimits.getExceptionsToTheAbove();

    if (Arrays.asList(exceptionsToTheAbove).contains(id)) {
      return true;
    } else if (Arrays.stream(unacceptableIds)
        .map(thisId -> thisId.charAt(0))
        .toList()
        .contains(id.charAt(0))) { // search if the first letter matches any in unacceptable
      // if so, check what the full match was - if its 2xx (e.g.), we've already rules out it's not
      // exempt
      char firstLetter = id.charAt(0);
      List<String> potentialMatches =
          Arrays.stream(unacceptableIds).filter(thisId -> thisId.charAt(0) == firstLetter).toList();
      if (potentialMatches.contains(firstLetter + "xx")) {
        return false;
      } else return !potentialMatches.contains(id);
    } else {
      return true;
    }
  }

  private boolean isTempWithinLimits(WeatherData weatherData) {
    return (int) weatherData.getFeelsLike() < boatLimits.getFeelsLikeTempMaxKelvin()
        && (int) weatherData.getFeelsLike() > boatLimits.getFeelsLikeTempMinKelvin();
  }
}
