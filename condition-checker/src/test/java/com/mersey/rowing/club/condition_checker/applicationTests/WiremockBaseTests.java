package com.mersey.rowing.club.condition_checker.applicationTests;

import static com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils.TEST_EPOCH_TIME;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockConfiguration;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockSetup;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(
    properties = {"open-weather-api.key=test", "open-weather-api.baseUrl=http://localhost:5050"})
@Import(WireMockConfiguration.class)
@Slf4j
@Tag("wiremock")
public class WiremockBaseTests {

  protected static OpenWeatherResponse expectedGenericOpenWeatherResponse;
  protected static String url;
  protected static String expectedResponseBody;

  @BeforeAll
  static void setUpBeforeClass() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.baseURI = TestOpenWeatherUtils.BASE_URL;

    url =
        TestOpenWeatherUtils.BASE_URL
            + "/timemachine?lat=53.39293&lon=-2.98532&dt="
            + TEST_EPOCH_TIME
            + "&appid="
            + TestOpenWeatherUtils.DUMMY_API_KEY;

    log.info("<<<<< SETTING UP WIREMOCK MAPPINGS >>>>>");
    WireMockSetup mockSetup = new WireMockSetup();
    MappingBuilder mappingBuilder = mockSetup.setupOpenWeatherMapping();
    expectedResponseBody = TestOpenWeatherUtils.getOpenWeatherResponseAsString(TEST_EPOCH_TIME);

    WireMock wireMock = new WireMock("localhost", 5050);
    wireMock.register(mappingBuilder);
    log.info("<<<<< WIREMOCK MAPPINGS COMPLETE SETUP >>>>>");

    expectedGenericOpenWeatherResponse =
        TestOpenWeatherUtils.getOpenWeatherResponse(TEST_EPOCH_TIME);
  }

  @AfterAll
  static void tearDownAfterClass() {
    log.info("<<<<< CLEARING WIREMOCK MAPPINGS >>>>>");
    WireMock wireMock = new WireMock("localhost", 5050);
    wireMock.resetMappings();
    log.info("<<<<< WIREMOCK MAPPINGS CLEARED >>>>>");
  }
}
