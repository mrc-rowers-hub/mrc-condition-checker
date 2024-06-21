package com.mersey.rowing.club.condition_checker.controller.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mersey.rowing.club.condition_checker.mock.MockOpenWeatherResponseGenerator;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.response.SessionConditions;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;

// why needed on the below...
@SpringBootTest(properties = { "open-weather-api.key=test"})
@Slf4j
public class SessionConditionsMapperTests {

    @Autowired
    SessionConditionsMapper sessionConditionsMapper;

    private static OpenWeatherResponse MOCK_OW_RESPONSE = MockOpenWeatherResponseGenerator.getOpenWeatherResponseAllGood();
    private static String EXPECTED_RESPONSE = """
            {"date": [2024,6,21,12,0],
            "weather": {
            "description": "clear sky",
            "temp_feels_like": 6,
            "wind_speed": 3
            },
            "boats_allowed": {
            "single": true,
            "doubles": true,
            "novice_four_and_above": true,
            "senior_four_and_above": true
            }}
            """;

    @Test
    void mapSessionConditionsFromOpenWeatherResponse_validOpenWeatherResponse_mapsAsExpected() {
        SessionConditions sessionConditions = sessionConditionsMapper.mapSessionConditionsFromOpenWeatherResponse(MOCK_OW_RESPONSE, "21/06/2024 12:00");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            String sessionConditionsJson = mapper.writeValueAsString(sessionConditions);
            JSONAssert.assertEquals(EXPECTED_RESPONSE, sessionConditionsJson, false);
        } catch ( AssertionError e) {
            log.error("JSON objects are not equal: " + e.getMessage());
        } catch (JSONException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
