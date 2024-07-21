package com.mersey.rowing.club.condition_checker.applicationTests;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockConfiguration;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockSetup;
import com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(
    properties = {"open-weather-api.key=test", "open-weather-api.baseUrl=http://localhost:5050"})
@Import(WireMockConfiguration.class)
@Slf4j
public class WireMockSpecificDtBaseTests {

  protected static String url;

  @Value("${open-weather-api.endpointNoSetDt}")
  private String endpoint;

  private WireMockSetup mockSetup = new WireMockSetup();
  private WireMock wireMock = new WireMock("localhost", 5050);

  @BeforeAll
  static void setUpBeforeClass() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.baseURI = TestOpenWeatherUtils.BASE_URL;

    log.info("<<<<< CLEARING WIREMOCK MAPPINGS >>>>>");
    WireMock wireMock = new WireMock("localhost", 5050);
    wireMock.resetMappings();
    log.info("<<<<< WIREMOCK MAPPINGS CLEARED >>>>>");
  }

  @Test
  void something() {
    setupWiremockMappingForDt(1719928728);
  }

  protected String formatUrl(int dt) {
    return TestOpenWeatherUtils.BASE_URL
        + endpoint.formatted(dt, TestOpenWeatherUtils.DUMMY_API_KEY);
  }

  protected void setupWiremockMappingForDt(int dt) {
    url = formatUrl(dt);
    log.info("URL to mock " + url);
    log.info("<<<<< SETTING UP WIREMOCK MAPPINGS >>>>>");
    MappingBuilder mappingBuilder = mockSetup.setupOpenWeatherMappingForDt(dt);
    wireMock.register(mappingBuilder);
    log.info("<<<<< WIREMOCK MAPPINGS COMPLETE SETUP >>>>>");
  }

  protected void setupUnauthorisedWiremockMapping() {
    log.info("<<<<< SETTING UP WIREMOCK MAPPINGS >>>>>");
    log.info("Mocking unauthorised response");
    MappingBuilder mappingBuilder = mockSetup.setup401Response();
    wireMock.register(mappingBuilder);
    log.info("<<<<< WIREMOCK MAPPINGS COMPLETE SETUP >>>>>");
  }
}
