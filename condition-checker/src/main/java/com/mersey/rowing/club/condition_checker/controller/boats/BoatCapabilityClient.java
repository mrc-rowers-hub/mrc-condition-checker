package com.mersey.rowing.club.condition_checker.controller.boats;

import com.mersey.rowing.club.condition_checker.model.boat.BoatLimits;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class BoatCapabilityClient {

    @Autowired
    BoatLimits boatLimits;

    // Todo remove redundant test method
    public void getBoatLimit(){
        log.info(Arrays.toString(boatLimits.getUnacceptableIds()));
    }

}
