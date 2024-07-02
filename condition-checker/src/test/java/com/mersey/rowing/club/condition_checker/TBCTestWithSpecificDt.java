package com.mersey.rowing.club.condition_checker;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockConfiguration;
import com.mersey.rowing.club.condition_checker.mockSetup.WireMockSetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = { "open-weather-api.key=test","open-weather-api.baseUrl=http://localhost:5050" })
@Import(WireMockConfiguration.class)
@Slf4j
public class TBCTestWithSpecificDt {

    @Autowired
    private WireMock wireMock;

    @Autowired
    private WireMockSetup wireMockSetup;

    @Value("${open-weather-api.endpoint}")
    private String endpoint;

    private static final String BASE_URL = "http://localhost:5050";
    private static final String DUMMY_API_KEY = "testApiKey";



}
