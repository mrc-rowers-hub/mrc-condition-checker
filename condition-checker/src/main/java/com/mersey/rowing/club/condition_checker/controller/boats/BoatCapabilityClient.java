package com.mersey.rowing.club.condition_checker.controller.boats;

import com.mersey.rowing.club.condition_checker.model.boat.BoatLimits;
import com.mersey.rowing.club.condition_checker.model.boat.BoatType;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.Weather;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.WeatherData;
import com.mersey.rowing.club.condition_checker.model.response.BoatsAllowed;
import com.mersey.rowing.club.condition_checker.model.response.WeatherConditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class BoatCapabilityClient {

    @Autowired
    BoatLimits boatLimits;

    // Todo remove redundant test method
    public void getBoatLimit() {
        log.info(Arrays.toString(boatLimits.getUnacceptableIds()));
    }

    public BoatsAllowed getBoatsAllowed(OpenWeatherResponse openWeatherResponse) { // Todo this will treturn BoatsAllows
        WeatherData weatherData = openWeatherResponse.getData().getFirst();
        Weather something = weatherData.getWeather().getFirst(); // rename
        // hm, reassess the below, only accounting for one weather data response
        WeatherConditions weatherConditions = WeatherConditions.builder().description(something.getDescription()).tempFeelsLike(convertToKelvin(weatherData.getFeelsLike())).windSpeed((int) weatherData.getWindSpeed()).build();

        // assess feels like, if its above or below the limits, no boats will be going out
        if (!isTempWithinLimits(weatherData)) {
            return BoatsAllowed.builder().doubles(false).single(false).noviceFourAndAbove(false).seniorFourAndAbove(false).build();
        } else if (!isIdWithinLimits(something)) {
            return BoatsAllowed.builder().doubles(false).single(false).noviceFourAndAbove(false).seniorFourAndAbove(false).build();
        } else {
            return getBoatsAllowedByWind((int) weatherData.getWindSpeed(), boatLimits.getBoatTypeWindLimit());
        }
    }

    private BoatsAllowed getBoatsAllowedByWind(int actualWindSpeed, Map<BoatType, Integer> boatsAndLimits) {
        // start with the boats that we know have the highest limits, as we can stop earlier if they're unable
        // Todo refactor this...
        BoatsAllowed.BoatsAllowedBuilder boatsAllowedBuilder = BoatsAllowed.builder();

        if (boatsAndLimits.get(BoatType.SENIOR_FOUR_AND_ABOVE) > actualWindSpeed) {
            return boatsAllowedBuilder.seniorFourAndAbove(false).noviceFourAndAbove(false).single(false).doubles(false).build();
        }
        boatsAllowedBuilder.seniorFourAndAbove(true);
        if (boatsAndLimits.get(BoatType.NOVICE_FOUR_AND_ABOVE) > actualWindSpeed) {
            return boatsAllowedBuilder.noviceFourAndAbove(false).single(false).doubles(false).build();
        }
        boatsAllowedBuilder.noviceFourAndAbove(false);
        if (boatsAndLimits.get(BoatType.DOUBLE) > actualWindSpeed) {
            return boatsAllowedBuilder.single(false).doubles(false).build();
        }
        boatsAllowedBuilder.doubles(true);
        boolean singlesAllowed = boatsAndLimits.get(BoatType.SINGLE) < actualWindSpeed;
        return boatsAllowedBuilder.single(singlesAllowed).build();
    }

    private boolean isIdWithinLimits(Weather weather) {
        String id = String.valueOf(weather.getId());

        String[] unacceptableIds = boatLimits.getUnacceptableIds();
        String[] exceptionsToTheAbove = boatLimits.getExceptionsToTheAbove();

        if (Arrays.asList(exceptionsToTheAbove).contains(id)) {
            return true;
        } else if (Arrays.stream(unacceptableIds)
                .map(thisId -> thisId.charAt(0))
                .toList().contains(id.charAt(0))) { // search if the first letter matches any in unacceptable
            // if so, check what the full match was - if its 2xx (e.g.), we've already rules out it's not exempt
            char firstLetter = id.charAt(0);
            List<String> potentialMatches = Arrays.stream(unacceptableIds).filter(thisId -> thisId.charAt(0) == firstLetter).toList();
            if (potentialMatches.contains(firstLetter + "xx")) {
                return false;
            } else return !potentialMatches.contains(id);
        } else {
            return true;
        }
    }

    private boolean isTempWithinLimits(WeatherData weatherData) {
        return (int) weatherData.getFeelsLike() < boatLimits.getFeelsLikeTempMaxKelvin() && (int) weatherData.getFeelsLike() > boatLimits.getFeelsLikeTempMinKelvin();
    }

    private int convertToKelvin(double feelsLike) {
        double kelvin = feelsLike - 273.15;
        return (int) kelvin;
    }


}
