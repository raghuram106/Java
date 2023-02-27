package com.cognizant.mfpe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PolicyServiceApplicationTests {
	
	PolicyServiceApplication policyServiceApplication;
	@Test
	void contextLoads() {
		assertTrue(true);
	}
	
	@Test
	void testMicroserviceApplication() {
		assertThat(policyServiceApplication).isNull();
	}
	
	


}
