package com.mersey.rowing.club.condition_checker.controller.openweather;

import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class OpenWeatherApiClient {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${open-weather-api.key}")
    private String apiKey;

    @Value("${open-weather-api.baseUrl}")
    private String apiBaseUrl;

    @Value("${open-weather-api.endpoint}")
    private String apiEndpoint;


    public OpenWeatherResponse getOpenWeatherAPIResponse() {
        String url = String.format(apiBaseUrl + apiEndpoint, apiKey);
        Class<OpenWeatherResponse> responseType = OpenWeatherResponse.class;

        try {
            OpenWeatherResponse openWeatherResponse = restTemplate.getForObject(url, responseType);
            log.info("Successfully retrieved and mapped response from open weather API");
            return openWeatherResponse;
        } catch (RestClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("Resource not found at " + url);
            } else {
                log.error("Error occurred during request: " + e.getMessage());
            }
            return null;
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
            return null;
        }
    }
}
