package com.mersey.rowing.club.condition_checker.mockSetup;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.mersey.rowing.club.condition_checker.utils.TestUtils;


public class WireMockSetup {

    private final String endpoint = "/timemachine";

    private final TestUtils testUtils = new TestUtils();

    public MappingBuilder setupGenericOpenWeatherMapping() {
        String body = testUtils.getOpenWeatherResponseAsString(null);

        return get(urlPathMatching(endpoint)).willReturn(aResponse().withStatus(200).withBody(body).withHeader("Content-Type", "application/json"));
    }

    public MappingBuilder setupOpenWeatherMappingForDt(int epochDateTime) {
        String body = testUtils.getOpenWeatherResponseAsString(String.valueOf(epochDateTime));
        return get(urlPathMatching(endpoint)).withQueryParam("dt", equalTo(String.valueOf(epochDateTime))).willReturn(aResponse().withStatus(200).withBody(body).withHeader("Content-Type", "application/json"));
    }

}
