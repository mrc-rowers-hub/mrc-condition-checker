package com.mersey.rowing.club.condition_checker.mockSetup;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils.TEST_EPOCH_TIME;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.mersey.rowing.club.condition_checker.utils.TestOpenWeatherUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WireMockSetup {

    private final String endpoint = "/timemachine";

    public MappingBuilder setupOpenWeatherMapping() {
        String body = TestOpenWeatherUtils.getOpenWeatherResponseAsString(TEST_EPOCH_TIME);
        return get(urlPathMatching(endpoint)).willReturn(aResponse().withStatus(200).withBody(body).withHeader("Content-Type", "application/json"));
    }

    public MappingBuilder setupOpenWeatherMappingForDt(int epochDateTime) {
        String body = TestOpenWeatherUtils.getOpenWeatherResponseAsString(epochDateTime);
        return get(urlPathMatching(endpoint)).withQueryParam("dt", equalTo(String.valueOf(epochDateTime))).willReturn(aResponse().withStatus(200).withBody(body).withHeader("Content-Type", "application/json"));
    }

}
