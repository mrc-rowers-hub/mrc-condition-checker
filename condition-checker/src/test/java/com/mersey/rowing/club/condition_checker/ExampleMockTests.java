package com.mersey.rowing.club.condition_checker;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.mersey.rowing.club.condition_checker.mock.WireMockConfiguration;
import com.mersey.rowing.club.condition_checker.mock.WireMockSetup;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static io.restassured.RestAssured.when;

@SpringBootTest()
@Import(WireMockConfiguration.class)
@Slf4j
public class ExampleMockTests {

    @Autowired
    private WireMock wireMock;

    @Autowired
    private WireMockSetup wireMockSetup;

    @Value("${open-weather-api.endpoint}")
    private String endpoint;

    private static final String BASE_URL = "http://localhost:5050";
    private static final String DUMMY_API_KEY = "testApiKey";
    private static final String TEST_EPOCH_TIME = "1625088000";

    private static String url;
    private static String expectedResponseBody;

    @BeforeAll
    static void setUpBeforeClass() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = BASE_URL;

        url = BASE_URL + "/timemachine?lat=53.39293&lon=-2.98532&dt=" + TEST_EPOCH_TIME + "&appid=" + DUMMY_API_KEY;

        log.info("<<<<< SETTING UP WIREMOCK MAPPINGS >>>>>");
        WireMockSetup mockSetup = new WireMockSetup();
        MappingBuilder mappingBuilder = mockSetup.setupOpenWeatherMapping();
        expectedResponseBody = mockSetup.getOpenWeatherResponseAsString();

        WireMock wireMock = new WireMock("localhost", 5050);
        wireMock.register(mappingBuilder);
        log.info("<<<<< WIREMOCK MAPPINGS COMPLETE SETUP >>>>>");
    }

    @Test
    void testWiremockMappingLive() throws JSONException {
        when().get(url)
                .then()
                .assertThat()
                .statusCode(200);

        String responseBody = when().get(url)
                .then()
                .extract()
                .body()
                .asString();

        JSONAssert.assertEquals(expectedResponseBody, responseBody, false);
    }

    @AfterAll
    static void tearDownAfterClass() {
        log.info("<<<<< CLEARING WIREMOCK MAPPINGS >>>>>");
        WireMock wireMock = new WireMock("localhost", 5050);
        wireMock.resetMappings();
        log.info("<<<<< WIREMOCK MAPPINGS CLEARED >>>>>");
    }
}