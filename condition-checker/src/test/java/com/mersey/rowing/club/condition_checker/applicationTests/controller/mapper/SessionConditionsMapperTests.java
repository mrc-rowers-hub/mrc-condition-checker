package com.mersey.rowing.club.condition_checker.applicationTests.controller.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mersey.rowing.club.condition_checker.controller.mapper.SessionConditionsMapper;
import com.mersey.rowing.club.condition_checker.mockSetup.MockOpenWeatherResponseGenerator;
import com.mersey.rowing.club.condition_checker.model.StatusCodeObject;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.model.response.SessionConditions;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootTest(properties = {"open-weather-api.key=test"})
public class SessionConditionsMapperTests {
  @Autowired SessionConditionsMapper sessionConditionsMapper;

  private static OpenWeatherResponse MOCK_OW_RESPONSE =
      MockOpenWeatherResponseGenerator.getOpenWeatherResponseAllGood();

  @Autowired
  private ObjectMapper mapper;

  private static String EXPECTED_RESPONSE =
      """
            {
            "status": "200 OK",
            "date": "17/06/2024 20:46",
            "weather_conditions": {
            "description": "clear sky",
            "temp_feels_like": 6,
            "wind_speed": 7
            },
            "boats_allowed": {
            "single": true,
            "doubles": true,
            "novice_four_and_above": true,
            "senior_four_and_above": true
            }}
            """;

  @BeforeEach
  public void init() {
    mapper.registerModule(new JavaTimeModule());
  }

  @Test
  void mapSessionConditionsFromOpenWeatherResponse_validOpenWeatherResponse_mapsAsExpected() {
    StatusCodeObject statusCodeObject = new StatusCodeObject(HttpStatus.OK, MOCK_OW_RESPONSE);

    SessionConditions actualSessionConditions =
        sessionConditionsMapper.mapFromStatusCodeObject(statusCodeObject);

    try {
      String sessionConditionsJson = mapper.writeValueAsString(actualSessionConditions);
      JSONAssert.assertEquals(EXPECTED_RESPONSE, sessionConditionsJson, false);
    } catch (AssertionError e) {
      log.error("JSON objects are not equal: " + e.getMessage());
      throw e;
    } catch (JSONException | JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void mapFromUnhappyOwResponse_401Response_mapsToSessionResponse() {
    StatusCodeObject statusCodeObject =
        new StatusCodeObject(HttpStatus.UNAUTHORIZED, "17/06/2024 20:46");
    SessionConditions actualSessionConditions =
        sessionConditionsMapper.mapFromStatusCodeObject(statusCodeObject);
    SessionConditions expectedSessionConditionsResponse =
        SessionConditions.builder()
            .status(HttpStatus.UNAUTHORIZED.toString())
            .date("17/06/2024 20:46")
            .build();
    try {
      String sessionConditionsJson = mapper.writeValueAsString(actualSessionConditions);
      String expectedSessionConditionsJson =
          mapper.writeValueAsString(expectedSessionConditionsResponse);
      JSONAssert.assertEquals(expectedSessionConditionsJson, sessionConditionsJson, false);
    } catch (AssertionError e) {
      log.error("JSON objects are not equal: " + e.getMessage());
      throw e;
    } catch (JSONException | JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
