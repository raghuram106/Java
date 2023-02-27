package com.mfpe.claims;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClaimServiceApplicationTests {

	ClaimServiceApplication claimServiceApplication;
	@Test
	void contextLoads() {
		assertTrue(true);
	}
	
	@Test
	void testMicroserviceApplication() {
		assertThat(claimServiceApplication).isNull();
	}
}
