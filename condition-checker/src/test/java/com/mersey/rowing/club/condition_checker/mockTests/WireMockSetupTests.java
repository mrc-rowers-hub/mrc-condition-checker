package com.mersey.rowing.club.condition_checker.mockTests;


import com.mersey.rowing.club.condition_checker.WiremockBaseTests;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static io.restassured.RestAssured.when;

public class WireMockSetupTests extends WiremockBaseTests {
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

}
