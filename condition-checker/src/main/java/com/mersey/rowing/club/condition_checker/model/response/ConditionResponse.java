package com.mersey.rowing.club.condition_checker.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class ConditionResponse {

    private SessionConditions sessionConditions;

}
