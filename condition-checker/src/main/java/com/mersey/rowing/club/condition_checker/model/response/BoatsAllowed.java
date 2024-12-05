package com.mersey.rowing.club.condition_checker.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class BoatsAllowed {

  private boolean single;

  private boolean doubles;

  @JsonProperty("quads")
  private boolean quads;

  @JsonProperty("eight")
  private boolean eight;
}
