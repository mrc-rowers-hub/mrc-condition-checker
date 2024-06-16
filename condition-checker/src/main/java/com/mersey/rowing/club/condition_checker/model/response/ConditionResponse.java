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
    // add additional field for any message to supply back, like 'something went wrong parsing request', etc

}
