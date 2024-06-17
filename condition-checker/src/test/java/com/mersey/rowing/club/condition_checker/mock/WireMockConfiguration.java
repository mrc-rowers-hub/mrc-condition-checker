package com.mersey.rowing.club.condition_checker.mock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WireMockConfiguration {
    private final String host;
    private final int port;

    public WireMockConfiguration(
            @Value("${wiremock.host}") String host, @Value("${wiremock.port}") int port) {
        this.host = host; // these values are set in the (test) app.yml
        this.port = port;
    }

    /**
     * Create a WireMock instance that connects to our standalone WireMock server. We need to specify
     * destroyMethod = "", so that Spring will not call WireMock.shutdown() (which is inferred) which
     * would lead to the standalone server being closed after every test run.
     *
     * @return A WireMock instance pointing at our standalone.
     */
    @Bean(destroyMethod = "")
    public WireMock wireMock() {
        return new WireMock(host, port);
    }

    @Bean
    public WireMockSetup wireMockSetup() {
        return new WireMockSetup();
    }
}
