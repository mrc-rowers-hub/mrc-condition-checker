package com.mersey.rowing.club.condition_checker.controller.boats;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoatCapabilityClientTests {

    @Autowired
    private BoatCapabilityClient boatCapabilityClient;


    @Test
    void contextLoads() {
        boatCapabilityClient.getBoatLimit();
    }
}
