package com.mersey.rowing.club.condition_checker.controller.boats;

import com.mersey.rowing.club.condition_checker.controller.boats.config.BoatConfig;
import com.mersey.rowing.club.condition_checker.model.boat.BoatLimits;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BoatCapabilityClient {

    @Autowired
    BoatLimits boatLimits;

    public void getBoatLimit(){
        log.info(boatLimits.toString());
    }


}
