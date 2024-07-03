package com.mersey.rowing.club.condition_checker.mockTests;

import com.mersey.rowing.club.condition_checker.applicationTests.WireMockSpecificDtBaseTests;
import com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static io.restassured.RestAssured.when;

public class WiremockSpecificDtSetupTests extends WireMockSpecificDtBaseTests {

    @Test
    void testWiremockMappingLive() throws JSONException {
        int dt =1719929099;
        String testUrl = formatUrl(dt);

        setupWiremockMappingForDt(dt);

        when().get(testUrl)
                .then()
                .assertThat()
                .statusCode(200);

        String responseBody = when().get(testUrl)
                .then()
                .extract()
                .body()
                .asString();

        String expectedResponseBody = TestOpenWeatherUtils.getOpenWeatherResponseAsString(dt);

        JSONAssert.assertEquals(expectedResponseBody, responseBody, false);
    }
}
