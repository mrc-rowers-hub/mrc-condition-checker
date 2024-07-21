package com.mersey.rowing.club.condition_checker.model.boat;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoatLimits {
  private int feelsLikeTempMinKelvin;
  private int feelsLikeTempMaxKelvin;
  private String[] unacceptableIds;
  private String[] exceptionsToTheAbove;
  private Map<BoatType, Integer> boatTypeWindLimit;
}
