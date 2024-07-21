package com.mersey.rowing.club.condition_checker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class StatusCodeObject {
  private final HttpStatus httpStatus;
  private final Object owResponse;
}
