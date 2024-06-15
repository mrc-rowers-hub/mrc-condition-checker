package com.mersey.rowing.club.condition_checker;

import com.mersey.rowing.club.condition_checker.controller.boats.BoatCapabilityClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConditionCheckerApplicationTests {

	@Test
	void contextLoads() {
		BoatCapabilityClient boatCapabilityClient = new BoatCapabilityClient();
//		boatCapabilityClient.getBoatConfig();
	}

}
