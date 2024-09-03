package com.mersey.rowing.club.condition_checker.controller.openweather;

import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import com.mersey.rowing.club.condition_checker.model.StatusCodeObject;
import com.mersey.rowing.club.condition_checker.model.openweatherapi.OpenWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class OpenWeatherApiClient {

  RestTemplate restTemplate = new RestTemplate();
  private static final DateTimeFormatter dtfMinusHours = DateTimeFormatter.ofPattern("dd/MM/yyyy");


  @Autowired private DateUtil dateUtil;

  @Value("${open-weather-api.key}")
  private String apiKey;

  @Value("${open-weather-api.baseUrl}")
  private String apiBaseUrl;

  @Value("${open-weather-api.endpoint}")
  private String apiEndpoint;

  public StatusCodeObject getOpenWeatherAPIResponse(long epoch) {
    String url = String.format(apiBaseUrl + apiEndpoint, epoch, apiKey);
    Class<OpenWeatherResponse> responseType = OpenWeatherResponse.class;
    try {
      checkDateAndAddCounter();
      OpenWeatherResponse openWeatherResponse = restTemplate.getForObject(url, responseType);
      log.info("Successfully retrieved and mapped response from open weather API");
      return new StatusCodeObject(HttpStatus.OK, openWeatherResponse);
    } catch (RestClientResponseException e) {
      log.error("Open Weather API gave an unexpected response: {}", e.getStatusCode());
      return new StatusCodeObject(
          (HttpStatus) e.getStatusCode(), dateUtil.getDatetimeFromEpochSeconds(epoch));
    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage());
      throw e;
    }
  }

  public String checkDateAndAddCounter() {
      BufferedReader bufferedReader = null;
      try {
          bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Sam\\Documents\\Code\\condition-checker\\condition-checker\\src\\main\\resources\\counter.txt"));
        // Skipping first line & grabbing date in file
        bufferedReader.readLine();
        String currentDate = bufferedReader.readLine();

        // Skipping third line and grabbing current number of API calls
        bufferedReader.readLine();
        Integer counter = Integer.valueOf(bufferedReader.readLine());
        bufferedReader.close();

        // Logic to update counter.txt
        if (dtfMinusHours.format(dateUtil.getCurrentDate()).equals(currentDate)) {
           counter++;
        } else {
          currentDate = dtfMinusHours.format(dateUtil.getCurrentDate());
          counter = 1;
        }

        if (counter > 900) {
          System.out.println("There are less than 100 calls left for today! Please use sparingly.");
            log.warn("There are only {} calls left", 1000 - counter);
        }

        // Opening and updating counter.txt
        openFileAndUpdateCounter(currentDate, counter);

        return currentDate;
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }

  private static void openFileAndUpdateCounter(String currentDate, Integer counter) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Sam\\Documents\\Code\\condition-checker\\condition-checker\\src\\main\\resources\\counter.txt"));

    bufferedWriter.write("Current Date:\n");
    bufferedWriter.write(currentDate + "\n");

    bufferedWriter.write("No. of API calls since above date:\n");
    bufferedWriter.write(String.valueOf(counter));
    bufferedWriter.close();
  }
}
