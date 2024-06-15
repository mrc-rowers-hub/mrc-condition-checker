package com.mersey.rowing.club.condition_checker.controller.boats;

import com.mersey.rowing.club.condition_checker.mock.MockOpenWeatherResponseGenerator;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.response.BoatsAllowed;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoatCapabilityClientTests {

    @Autowired
    private BoatCapabilityClient boatCapabilityClient;

    private final MockOpenWeatherResponseGenerator oWResponseGenerator = new MockOpenWeatherResponseGenerator();

    private BoatsAllowed.BoatsAllowedBuilder boatsAllowedBuilder;

    @BeforeEach
    public void setUp() {
        boatsAllowedBuilder = BoatsAllowed.builder();
    }


    @Test
    void contextLoads() {
//        log.info(String.valueOf(boatCapabilityClient.getBoatsAllowed(oWResponseGenerator.getOpenWeatherResponseGenericFeelsLike(274))));
    }


    // scenarios
    // temp above, below, and within range
    // wind speed above limit, and within
    // weather code acceptable, exlusion, non-acceptable
    // variety of wind speeds
}
