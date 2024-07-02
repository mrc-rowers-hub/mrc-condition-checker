package com.mersey.rowing.club.condition_checker;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockConfiguration;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockSetup;
import com.mersey.rowing.club.condition_checker.utils.TestUtils;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = { "open-weather-api.key=test","open-weather-api.baseUrl=http://localhost:5050" })
@Import(WireMockConfiguration.class)
@Slf4j
public class WireMockSpecificDtBaseTests  {

    protected static TestUtils testUtils = new TestUtils();
    protected static String url;
    protected static String expectedResponseBody;

    @Value("${open-weather-api.endpointNoSetDt}")
    private String endpoint;

    private WireMockSetup mockSetup = new WireMockSetup();
    private WireMock wireMock = new WireMock("localhost", 5050);

    @BeforeAll
    static void setUpBeforeClass() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = Context.BASE_URL;

        log.info("<<<<< CLEARING WIREMOCK MAPPINGS >>>>>");
        WireMock wireMock = new WireMock("localhost", 5050);
        wireMock.resetMappings();
        log.info("<<<<< WIREMOCK MAPPINGS CLEARED >>>>>");
    }

    @Test
    void something(){
        setupWiremockMappingForDt(1719928728);
    }

    protected String formatUrl(int dt){
        return Context.BASE_URL + endpoint.formatted(dt,Context.DUMMY_API_KEY);
    }

    protected void setupWiremockMappingForDt(int dt){
        url = formatUrl(dt);
        log.info("URL to mock " + url);
        log.info("<<<<< SETTING UP WIREMOCK MAPPINGS >>>>>");
        MappingBuilder mappingBuilder = mockSetup.setupOpenWeatherMappingForDt(dt);
        expectedResponseBody = testUtils.getOpenWeatherResponseAsString(String.valueOf(dt));
        System.out.println(expectedResponseBody);
        wireMock.register(mappingBuilder);
        log.info("<<<<< WIREMOCK MAPPINGS COMPLETE SETUP >>>>>");
    }

}
