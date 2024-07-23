package com.mersey.rowing.club.condition_checker.controller.response;

import com.mersey.rowing.club.condition_checker.controller.mapper.SessionConditionsMapper;
import com.mersey.rowing.club.condition_checker.controller.openweather.OpenWeatherApiClient;
import com.mersey.rowing.club.condition_checker.controller.util.DateUtil;
import com.mersey.rowing.club.condition_checker.model.response.ConditionResponse;
import com.mersey.rowing.club.condition_checker.model.response.SessionConditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ConditionResponseClient {

  private final LocalDate dateToday = LocalDate.now();

  @Autowired private OpenWeatherApiClient owac;

  @Autowired private DateUtil dateUtil;

  @Autowired SessionConditionsMapper sessionConditionsMapper;

  public ResponseEntity<ConditionResponse> getConditionResponseFromDateTime(
      String date, String time) {
    List<SessionConditions> sessionConditionsList = new ArrayList<>();
    // the first time = session, the others are 'during' the session
    long[] epochs = getEpochBasedOnLogic(date, time);
    // iterate over map
    // for each 'key' - set uuid
    // for each key - do session conditions with time type of start
    // then grab the values
    // for each of those, organise/sort it, then set the first one as DURING_SESSION
    // second SESSION_END

    for (long epoch : epochs) {
      SessionConditions thisTimeResponse =
          sessionConditionsMapper.mapFromStatusCodeObject(owac.getOpenWeatherAPIResponse(epoch));
      sessionConditionsList.add(thisTimeResponse);
    }

    ConditionResponse response =
        ConditionResponse.builder().sessionConditions(sessionConditionsList).build();
    return ResponseEntity.ok(response);
  }

  //  public

  private long[] getEpochBasedOnLogic(String date, String time) {
    if ((date == null && time == null) || (dateToday.toString().equals(date) && time == null)) {
      return dateUtil.getEpochsPlusTwoHoursToEach(
          dateUtil.getEpochsDateNullAndTimeNull()); // plus two hours for each
    } else if (date == null) {
      return dateUtil.getEpochsPlusTwoHoursToEach(dateUtil.getEpochsDateOnlyIsNull(time));
    } else if (time == null) {
      return dateUtil.getEpochsPlusTwoHoursToEach(dateUtil.getEpochsTimeOnlyIsNull(date));
    } else {
      return dateUtil.getEpochsPlusTwoHoursToEach(
          dateUtil.getEpochsDateAndTimeSupplied(date, time));
    }
  }

  private Map<Long, long[]> getEpochBasedOnLogicWithSessionStart(String date, String time) {
    List<Long> sessionStarts = new ArrayList<>();
    Map<Long, long[]> map = new HashMap<>();
    if ((date == null && time == null) || (dateToday.toString().equals(date) && time == null)) {
      sessionStarts = dateUtil.getEpochsDateNullAndTimeNullASLIST();
    } else if (date == null) {
      sessionStarts = dateUtil.getEpochsDateOnlyIsNullASLIST(time);
    } else if (time == null) {
      sessionStarts = dateUtil.getEpochsTimeOnlyIsNullASLIST(date);
    } else {
      sessionStarts = dateUtil.getEpochsDateAndTimeSuppliedASLIST(date, time);
    }
    for (Long startTime : sessionStarts) {
      map.put(startTime, dateUtil.getEpochPlusOneAndTwoHours(startTime));
    }
    return map;
  }
}
