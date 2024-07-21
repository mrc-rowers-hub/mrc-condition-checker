package com.mersey.rowing.club.condition_checker.applicationTests.controller.openWeather;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mersey.rowing.club.condition_checker.applicationTests.WireMockSpecificDtBaseTests;
import com.mersey.rowing.club.condition_checker.applicationTests.WiremockBaseTests;
import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;


import static com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils.TEST_EPOCH_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenWeatherApiClientTests extends WireMockSpecificDtBaseTests {

    @Autowired
    private OpenWeatherApiClient openWeatherApiClient;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getOpenWeatherAPIResponse_apiGivesExpectedResponse_mapsToOWResponse_withCorrectDT() throws JsonProcessingException, JSONException {
        setupWiremockMappingForDt(1720626363);

        OpenWeatherResponse actualMappedResponse = openWeatherApiClient.getOpenWeatherAPIResponse(1720626363L);
        String actualJson = objectMapper.writeValueAsString(actualMappedResponse);
        String expectedJson = TestOpenWeatherUtils.getOpenWeatherResponseAsString(1720626363);
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    // Todo API giving 500 doesn't map, should return null perhaps?

}
