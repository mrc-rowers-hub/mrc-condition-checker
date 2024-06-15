package com.mersey.rowing.club.condition_checker.controller.boats.config;


import com.mersey.rowing.club.condition_checker.model.boat.BoatLimits;
import com.mersey.rowing.club.condition_checker.model.boat.BoatType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

import static com.mersey.rowing.club.condition_checker.model.boat.BoatType.*;

@Configuration
@Slf4j
public class BoatConfig {

    @Value("${boats.feelsLikeTempMinKelvin}")
    private int feelsLikeTempMinKelvin;

    @Value("${boats.feelsLikeTempMaxKelvin}")
    private int feelsLikeTempMaxKelvin;

    @Value("${boats.unacceptableIds}")
    private String[] unacceptableIdsString;

    @Value("${boats.exceptionsToTheAbove}")
    private String[] exceptionsToTheAboveString;

    @Value("${boats.boatTypes.single.wind}")
    private int singleWindLimit;

    @Value("${boats.boatTypes.double.wind}")
    private int doubleWindLimit;

    @Value("${boats.boatTypes.seniorFourAndAbove.wind}")
    private int seniorFourAboveWindLimit;

    @Value("${boats.boatTypes.noviceFourAndAbove.wind}")
    private int noviceFourAboveWindLimit;

    @Bean
    public BoatLimits getBoatConfig() {
        log.info("ADDI: ");
        log.info(Arrays.toString(unacceptableIdsString));
        //        BoatLimits boatLimits = new BoatLimits();
//        boatLimits.setFeelsLikeTempMinKelvin(feelsLikeTempMinKelvin);
//        log.info(String.valueOf(boatLimits.getFeelsLikeTempMaxKelvin()));
        return BoatLimits.builder().boatTypeWindLimit(Map.of(SINGLE, singleWindLimit, DOUBLE, doubleWindLimit, SENIOR_FOUR_AND_ABOVE, seniorFourAboveWindLimit, NOVICE_FOUR_AND_ABOVE, noviceFourAboveWindLimit )).feelsLikeTempMaxKelvin(feelsLikeTempMaxKelvin).feelsLikeTempMinKelvin(feelsLikeTempMinKelvin).build();
    }
}
